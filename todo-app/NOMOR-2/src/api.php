<?php
include 'db.php';

// Menangani GET (ambil semua tugas)
if ($_SERVER['REQUEST_METHOD'] == 'GET') {
    $result = $conn->query("SELECT * FROM todo_list");

    if (!$result) {
        die("Query failed: " . $conn->error);
    }

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

    // Query untuk menambahkan tugas baru
    $stmt = $conn->prepare("INSERT INTO todo_list (task, status) VALUES (?, ?)");
    $stmt->bind_param("ss", $task, $status);
    $stmt->execute();

    if ($stmt->affected_rows > 0) {
        echo json_encode(["message" => "Task added successfully"]);
    } else {
        echo json_encode(["message" => "Failed to add task"]);
    }

    $stmt->close();
}

// Menangani PUT (update tugas)
elseif ($_SERVER['REQUEST_METHOD'] == 'PUT') {
    $data = json_decode(file_get_contents("php://input"), true);
    $id = $data['id'];
    $task = $data['task'];
    $status = $data['status'];

    // Query untuk mengupdate tugas berdasarkan ID
    $stmt = $conn->prepare("UPDATE todo_list SET task = ?, status = ? WHERE id = ?");
    $stmt->bind_param("ssi", $task, $status, $id);
    $stmt->execute();

    if ($stmt->affected_rows > 0) {
        echo json_encode(["message" => "Task updated successfully"]);
    } else {
        echo json_encode(["message" => "Failed to update task or no changes made"]);
    }

    $stmt->close();
}

// Menangani DELETE (hapus tugas)
elseif ($_SERVER['REQUEST_METHOD'] == 'DELETE') {
    $data = json_decode(file_get_contents("php://input"), true);
    $id = $data['id'];

    // Query untuk menghapus tugas berdasarkan ID
    $stmt = $conn->prepare("DELETE FROM todo_list WHERE id = ?");
    $stmt->bind_param("i", $id);
    $stmt->execute();

    if ($stmt->affected_rows > 0) {
        echo json_encode(["message" => "Task deleted successfully"]);
    } else {
        echo json_encode(["message" => "Failed to delete task"]);
    }

    $stmt->close();
}

$conn->close();
?>
