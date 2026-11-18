import { baseURL } from "../constants";
import { useLocation } from "react-router-dom"; 

const AddProduct = () => {
    const location = useLocation();
    const product = location.state?.product;    
    // create submit handler for the form update this to handle both add and update cases
    const isUpdate = !!product;
    const handleSubmit = (event) => {
        event.preventDefault();

        const formData = new FormData(event.target);

        const productData = Object.fromEntries(formData.entries());
        productData.available = formData.get("available") === "on";
        if (isUpdate) {
            productData.id = product.id;
        }
        const data = new FormData();

        // Append product JSON
        data.append(
            "product",
            new Blob([JSON.stringify(productData)], {
                type: "application/json"
            })
        );

        // Append image file
        
        // data.append("imageFile", formData.get("imageFile"));
        const imageFile = formData.get("imageFile");

        if (imageFile && imageFile.size > 0) {
            data.append("imageFile", imageFile);
        }

        fetch(`${baseURL}/product${isUpdate ? `/${product.id}` : ""}`, {
            method: isUpdate ? "PUT" : "POST",
            body: data
        })
        .then(response => response.json())
        .then(data => console.log(data))
        .catch(error => console.error(error));
    };
    return (
        <div style={{display: "flex", flexDirection: "column", alignItems: "center"}}>
            <h1>{product ? "Update Product Page" : "Add Product Page"}</h1>
            <form onSubmit={handleSubmit} style={{display: "flex", flexDirection: "column", width: "300px"}}>
                <label htmlFor="name">Name:</label>
                <input type="text" id="name" name="name" defaultValue={product?.name} />
                <br />
                <label htmlFor="description">Description:</label>
                <textarea id="description" name="description" defaultValue={product?.description}></textarea>
                <br />
                <label htmlFor="price">Price:</label>
                <input type="number" id="price" name="price" defaultValue={product?.price} />
                <br />
                <label htmlFor="category">Category:</label> 
                <input type="text" id="category" name="category" defaultValue={product?.category} />
                <br />
                <label htmlFor="brand">Brand:</label>
                <input type="text" id="brand" name="brand" defaultValue={product?.brand} />
                <br />
                <label htmlFor="quantity">Quantity:</label>
                <input type="number" id="quantity" name="quantity" defaultValue={product?.quantity} />
                <br />
                <label htmlFor="releaseDate">Release Date:</label>
                <input type="date" id="releaseDate" name="releaseDate" defaultValue={product?.releaseDate?.split("T")[0]} />
                <br />
                <label htmlFor="available">Available:</label>
                <input style={{transform: "scale(1.5)"}} type="checkbox" id="available" name="available" defaultChecked={product?.available} />
                <br />
                <button type="submit">{product ? "Update Product" : "Add Product"}</button>
                <label htmlFor="image">Image:</label>
                  
                {product?.imageName && (
                    <div>
                        <p>Current Image: {product.imageName}</p>
                        <img src={`data:${product.imageType};base64,${product.imageData}`} alt={product.imageName} width="100" height="100" style={{objectFit: "cover", borderRadius: "5px"}} />
                    </div>
                )}
                <input type="file" id="image" name="imageFile" />
            </form>
        </div>
    );
}

export default AddProduct;
