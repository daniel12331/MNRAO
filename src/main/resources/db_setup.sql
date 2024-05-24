CREATE DATABASE IF NOT EXISTS mnrao;

USE mnrao;

CREATE TABLE resource_usage (
    node_id INT NOT NULL,
    network_id INT NOT NULL,
    cpu_usage DOUBLE NOT NULL,
    memory_usage DOUBLE NOT NULL,
    bandwidth_usage DOUBLE NOT NULL,
    cpu_allocated DOUBLE NOT NULL,
    memory_allocated DOUBLE NOT NULL,
    bandwidth_allocated DOUBLE NOT NULL,
    timestamp DATETIME NOT NULL
);

CREATE INDEX idx_node_id ON resource_usage (node_id);
CREATE INDEX idx_network_id ON resource_usage (network_id);
CREATE INDEX idx_timestamp ON resource_usage (timestamp);

INSERT INTO resource_usage (node_id, network_id, cpu_usage, memory_usage, bandwidth_usage, cpu_allocated, memory_allocated, bandwidth_allocated, timestamp)
VALUES
(1, 101, 55.5, 70.2, 30.5, 100.0, 100.0, 100.0, '2024-05-24 12:00:00');