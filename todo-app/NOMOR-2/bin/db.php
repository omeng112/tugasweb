<?php
// src/db.php

$servername = "localhost";
$username = "root";
$password = "";
$dbname = "todo_app";

// Membuat koneksi ke database
$conn = new mysqli($servername, $username, $password, $dbname);

// Memeriksa koneksi
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
?>
