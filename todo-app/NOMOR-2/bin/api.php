<?php
// src/api.php
include 'db.php';

// Menangani GET (ambil semua tugas)
if ($_SERVER['REQUEST_METHOD'] == 'GET') {
    $result = $conn->query("SELECT * FROM todo_list");
    $tasks = array();
    while ($row = $result->fetch_assoc()) {
        $tasks[] = $row;
    }
    echo json_encode($tasks);
}

// Menangani POST (tambah tugas)
elseif ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $data = json_decode(file_get_contents("php://input"), true);
    $task = $data['task'];
    $status = $data['status'];

    $stmt = $conn->prepare("INSERT INTO todo_list (task, status) VALUES (?, ?)");
    $stmt->bind_param("ss", $task, $status);
    $stmt->execute();

    echo json_encode(["message" => "Task added successfully"]);
}

// Menangani PUT (update tugas)
elseif ($_SERVER['REQUEST_METHOD'] == 'PUT') {
    $data = json_decode(file_get_contents("php://input"), true);
    $id = $data['id'];
    $task = $data['task'];
    $status = $data['status'];

    $stmt = $conn->prepare("UPDATE todo_list SET task = ?, status = ? WHERE id = ?");
    $stmt->bind_param("ssi", $task, $status, $id);
    $stmt->execute();

    echo json_encode(["message" => "Task updated successfully"]);
}

// Menangani DELETE (hapus tugas)
elseif ($_SERVER['REQUEST_METHOD'] == 'DELETE') {
    $data = json_decode(file_get_contents("php://input"), true);
    $id = $data['id'];

    $stmt = $conn->prepare("DELETE FROM todo_list WHERE id = ?");
    $stmt->bind_param("i", $id);
    $stmt->execute();

    echo json_encode(["message" => "Task deleted successfully"]);
}

$conn->close();
?>
