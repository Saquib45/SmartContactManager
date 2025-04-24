console.log("Hello from admin.js");
document.querySelector("#image_file_input").addEventListener("change", function(event) {
    let file = event.target.files[0]; // Fixed syntax
    if (!file) return; // Check if file exists to prevent errors
    
    let reader = new FileReader();
    reader.onload = function() {
        document.querySelector("#upload_image_preview").setAttribute("src", reader.result);
    };
    reader.readAsDataURL(file);
});

