import {useState, useEffect} from "react";
import { productURL } from "../constants";
import { useNavigate } from "react-router-dom";

const ProductCard = (product) => {
  const navigate = useNavigate();
  return (
    <div style={{border: "1px solid #ccc", padding: "10px", margin: "10px", borderRadius: "5px", width: "200px", textAlign: "center", display: "flex", flexDirection: "column", alignItems: "center", cursor: "pointer"}} onClick={() => navigate(`/product/${product.id}`)}>
     
    <img
        src={`data:${product.imageType};base64,${product.imageData}`}
        alt={product.imageName}
        width="100"
        height="100"
        style={{objectFit: "cover", borderRadius: "5px"}}
    />
      <h3 style={{margin: "0"}}>{product.name}</h3>
      <p style={{margin: "5px 0"}}>{product.description}</p>
      <p style={{margin: "5px 0"}}>${product.price.toFixed(2)}</p>
      <button style={{padding: "5px 10px", backgroundColor: "#007bff", color: "#fff", border: "none", borderRadius: "3px", cursor: "pointer"}}>Add to Cart</button> 
    </div>
  );
};

function Home() {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [searchTerm, setSearchTerm] = useState("");

  useEffect(() => {
    const controller = new AbortController();
    const fetchProducts = async () => {
      try {
        const response = await fetch(productURL, { signal: controller.signal });
        console.log("response", response);
        const data = await response.json();
        if (!controller.signal.aborted) setProducts(data);
      } catch (error) {
        if (!controller.signal.aborted) {
          console.error("Error fetching products:", error);
          setError(error);
        }
      } finally {
        if (!controller.signal.aborted) setLoading(false);
      }
    };
    fetchProducts();
    return () => controller.abort();
  }, []);

  const changeHandler = (e) => {
    setSearchTerm(e.target.value);
    console.log("searchTerm", e.target.value);
    const fetchProductsBySearchTerm = async () => {
      try {
        setLoading(true);
        const response = await fetch(`${productURL}/search?keyword=${searchTerm}`);
        const data = await response.json();
        setProducts(data);
      } catch (error) {
        console.error("Error fetching products:", error);
        setError(error);
      } finally {
        setLoading(false);
      }
    };
    fetchProductsBySearchTerm();
  };

  return <div >
    {loading && <p style={{color: "blue"}}>Loading products...</p>}
    {error && <p style={{color: "red"}}>Error fetching products: {error.message}</p>}
    <input type="text" placeholder="Search products..." style={{margin: "10px", padding: "5px", width: "200px"}} onChange={changeHandler} value={searchTerm} />
    <div style={{display: "flex", flexWrap: "wrap", justifyContent: "center"}}>
    {!loading && !error && products.filter(product => product.name.toLowerCase().includes(searchTerm.toLowerCase())).map((product) => (
      <ProductCard key={product.id} {...product} />
    ))}
    </div>
  </div>;
}

export default Home;