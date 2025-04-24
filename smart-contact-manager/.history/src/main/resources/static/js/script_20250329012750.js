console.log("Hello from script.js");
let currentTheme = getTheme();

document.addEventListener("DOMContentLoaded", () => {
    applyTheme(); // Apply the theme on page load

    const changeThemeButton = document.querySelector("#theme_change_button");
    if (changeThemeButton) {
        changeThemeButton.addEventListener("click", toggleTheme);
    }
});

function applyTheme() {
    document.querySelector("html").classList.add(currentTheme);
    updateButtonText();
}

function toggleTheme() {
    const oldTheme = currentTheme;
    
    currentTheme = currentTheme === "dark" ? "light" : "dark";
    setTheme(currentTheme);

    // Remove the old theme and apply the new one
    document.querySelector("html").classList.remove(oldTheme);
    document.querySelector("html").classList.add(currentTheme);

    updateButtonText();
}

function updateButtonText() {
    const changeThemeButton = document.querySelector("#theme_change_button");
    if (changeThemeButton) {
        changeThemeButton.querySelector("span").textContent = currentTheme === "dark" ? "Light" : "Dark";
    }
}

function setTheme(theme) {
    localStorage.setItem("theme", theme);
}

function getTheme() {
    return localStorage.getItem("theme") || "light"; // Default to "light"
}
