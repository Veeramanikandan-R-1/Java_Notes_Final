import { useParams } from "react-router-dom";
import {baseURL} from "../constants";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

const ProductDetail = () => {
    const prodId = useParams().id;
    const [prodData, setProdData] = useState(null);
    console.log("prodId", prodId);
    useEffect(() => {
        const fetchProductDetail = async () => {
            try {
                const response = await fetch(`${baseURL}/product/${prodId}`);
                const data = await response.json();
                setProdData(data);
                console.log("Product detail data:", data);
            } catch (error) {
                console.error("Error fetching product detail:", error);
            }
        };
        fetchProductDetail();
    }, [prodId]);

  const navigate = useNavigate();
  const updateHandler = () => {
    // Add your update logic here
    console.log("Update product with ID:", prodId);
    // in this case shall we pass the product data as state to the update page?
    navigate(`/add-product`, { state: { product: prodData } });
  };
  
  const deleteHandler = async () => {
    try {
      const response = await fetch(`${baseURL}/product/${prodId}`, {
        method: "DELETE"
      });
      if (response.ok) {
        console.log("Product deleted successfully");
        navigate("/"); // Redirect to home or product list page after deletion
      } else {
        console.error("Failed to delete product");
      }
    } catch (error) {
      console.error("Error deleting product:", error);
    }
  };

  return (
    <div>
      <h1>Product Details Page</h1>
      {prodData && (
        <div style={{border: "1px solid #ccc", padding: "10px", margin: "10px", borderRadius: "5px", width: "400px", textAlign: "left"}}>
          <img
            src={`data:${prodData.imageType};base64,${prodData.imageData}`}
            alt={prodData.imageName}
            width="150"
            height="150"
            style={{objectFit: "cover", borderRadius: "5px"}}
        />
          <h2>{prodData.name}</h2>
          <p>{prodData.description}</p>
          <p>Price: ${prodData.price?.toFixed(2)}</p>
          <p>Category: {prodData.category}</p>
          <p>Brand: {prodData.brand}</p>
          <p>Quantity: {prodData.quantity}</p>
          <p>Release Date: {new Date(prodData.releaseDate).toLocaleDateString()}</p>
          <p>Available: {prodData.available ? "Yes" : "No"}</p>
          <button style={{padding: "5px 10px", backgroundColor: "#007bff", color: "#fff", border: "none", borderRadius: "3px", cursor: "pointer"}} onClick={updateHandler} >Update Product</button>
          <button style={{padding: "5px 10px", backgroundColor: "#dc3545", color: "#fff", border: "none", borderRadius: "3px", cursor: "pointer", marginLeft: "10px"}} onClick={deleteHandler} >Delete Product</button>
        </div>
      )}
    </div>
  );
}

export default ProductDetail;