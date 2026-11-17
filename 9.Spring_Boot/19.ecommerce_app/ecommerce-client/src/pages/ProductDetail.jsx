import { useParams } from "react-router-dom";
import {baseURL} from "../constants";
import { useEffect, useState } from "react";

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
  return (
    <div>
      <h1>Product Details Page</h1>
      {prodData && (
        <div style={{border: "1px solid #ccc", padding: "10px", margin: "10px", borderRadius: "5px", width: "400px", textAlign: "left"}}>
          <h2>{prodData.name}</h2>
          <p>{prodData.description}</p>
          <p>Price: ${prodData.price.toFixed(2)}</p>
          <p>Category: {prodData.category}</p>
          <p>Brand: {prodData.brand}</p>
          <p>Quantity: {prodData.quantity}</p>
          <p>Release Date: {new Date(prodData.releaseDate).toLocaleDateString()}</p>
          <p>Available: {prodData.available ? "Yes" : "No"}</p>
          <button style={{padding: "5px 10px", backgroundColor: "#007bff", color: "#fff", border: "none", borderRadius: "3px", cursor: "pointer"}}>Update Product</button>
          <button style={{padding: "5px 10px", backgroundColor: "#dc3545", color: "#fff", border: "none", borderRadius: "3px", cursor: "pointer", marginLeft: "10px"}}>Delete Product</button>
        </div>
      )}
    </div>
  );
}

export default ProductDetail;