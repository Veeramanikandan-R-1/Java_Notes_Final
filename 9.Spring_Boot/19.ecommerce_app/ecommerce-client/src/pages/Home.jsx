import {useState, useEffect} from "react";
import { productURL } from "../constants";
import { useNavigate } from "react-router-dom";

const ProductCard = (product) => {
  const navigate = useNavigate();
  return (
    <div style={{border: "1px solid #ccc", padding: "10px", margin: "10px", borderRadius: "5px", width: "200px", textAlign: "center", display: "flex", flexDirection: "column", alignItems: "center", cursor: "pointer"}} onClick={() => navigate(`/product/${product.id}`)}>
      <h3 style={{margin: "0"}}>{product.name}</h3>
      <p style={{margin: "5px 0"}}>{product.description}</p>
      <p style={{margin: "5px 0"}}>${product.price.toFixed(2)}</p>
      <button style={{padding: "5px 10px", backgroundColor: "#007bff", color: "#fff", border: "none", borderRadius: "3px", cursor: "pointer"}}>Add to Cart</button> 
    </div>
  );
};

function Home() {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    // Fetch products from API
    try{
      const fetchProducts = async () => {
        setLoading(true);
        const response = await fetch(productURL);
        console.log("response", response);
        const data = await response.json();
        setProducts(data);
      };
      fetchProducts();
    }catch(error){
      console.error("Error fetching products:", error);
      setError(error);
    }finally{
      setLoading(false);
    }
  }, []);

  return <div style={{display: "flex", flexWrap: "wrap", justifyContent: "center"}}>
    {loading && <p style={{color: "blue"}}>Loading products...</p>}
    {error && <p style={{color: "red"}}>Error fetching products: {error.message}</p>}
    {!loading && !error && products.map((product) => (
      <ProductCard key={product.id} {...product} />
    ))}
  </div>;
}

export default Home;