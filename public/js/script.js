$(document).ready(function(){
    const myCarouselElement = document.querySelector('#carouselExample')

    const carousel = new bootstrap.Carousel(myCarouselElement, {
        interval: false
    })
    const data = [
        { nodeId: 1, networkId: 101, cpuUsage: 20.5, memoryUsage: 25.0, bandwidthUsage: 15.0, cpuAllocated: 50, memoryAllocated: 50, bandwidthAllocated: 50},
        { nodeId: 2, networkId: 101, cpuUsage: 30.3, memoryUsage: 35.0, bandwidthUsage: 25.0, cpuAllocated: 50, memoryAllocated: 50, bandwidthAllocated: 50},
        { nodeId: 3, networkId: 101, cpuUsage: 30.1, memoryUsage: 45.0, bandwidthUsage: 35.0, cpuAllocated: 50, memoryAllocated: 50, bandwidthAllocated: 50},
        { nodeId: 4, networkId: 101, cpuUsage: 24.1, memoryUsage: 40.0, bandwidthUsage: 20.0, cpuAllocated: 50, memoryAllocated: 50, bandwidthAllocated: 50},
        { nodeId: 5, networkId: 101, cpuUsage: 30.1, memoryUsage: 50.0, bandwidthUsage: 45.0, cpuAllocated: 50, memoryAllocated: 50, bandwidthAllocated: 50},
    ];

    const labels = data.map(d => d.nodeId);
    const cpuUsageData = data.map(d => d.cpuUsage);
    const cpuAllocationData = data.map(d => d.cpuAllocated);

    const memoryUsageData = data.map(d => d.memoryUsage);
    const memAllocationData = data.map(d => d.memoryAllocated);

    const bandwidthUsageData = data.map(d => d.bandwidthUsage);
    const bandAllocationData = data.map(d => d.bandwidthAllocated);

    const cpuctx = document.getElementById('cpuChart').getContext('2d');
    const cpuUsageChart = new Chart(cpuctx, {
        type: 'line',
        data: {
            labels: labels,
            datasets: [
                {
                    label: 'CPU Usage (%)',
                    data: cpuUsageData,
                    borderColor: 'rgba(75, 192, 192, 1)',
                    backgroundColor: 'rgba(75, 192, 192, 0.2)',
                    borderWidth: 3,
                    fill: false,
                    tension: 0
                },
                {
                    label: 'CPU Allocation (%)',
                    data: cpuAllocationData,
                    borderColor: 'red',
                    backgroundColor: 'rgba(192, 75, 75, 0.2)',
                    borderWidth: 3,
                    fill: false,
                    tension: 0
                }
            ]
        },
        options: {
            title: {
                display: true,
                text: 'CPU Usage Data On Nodes',
                fontSize: 36,
                fontColor: 'black'
            },
            onClick: function(event, elements) {
                if (elements.length > 0) {
                    var index = elements[0]._index;
                    var networkID = labels[index];
                    hideAndShowGraph("showRec", networkID)
                }
            }
        }
    });

    const memctx = document.getElementById('memoryChart').getContext('2d');
    const memUsageChart = new Chart(memctx, {
        type: 'line',
        data: {
            labels: labels,
            datasets: [
                {
                    label: 'Memory Usage (%)',
                    data: memoryUsageData,
                    borderColor: 'rgb(59,46,174)',
                    backgroundColor: 'rgba(75, 192, 192, 0.2)',
                    borderWidth: 3,
                    fill: false,
                    tension: 0
                },
                {
                    label: 'Memory Allocation (%)',
                    data: memAllocationData,
                    borderColor: 'red',
                    backgroundColor: 'rgba(192, 75, 75, 0.2)',
                    borderWidth: 3,
                    fill: false,
                    tension: 0
                }
            ]
        },
        options: {
            title: {
                display: true,
                text: 'Memory Usage Data On Nodes',
                fontSize: 36,
                fontColor: 'black'
            },
            scales: {
                x: {
                    title: {
                        display: true,
                        text: 'Node ID',

                    }
                },
                y: {
                    title: {
                        display: true,
                        text: 'Usage (%)'
                    },
                    beginAtZero: true
                }
            }
        }
    });

    const bandctx = document.getElementById('bandChart').getContext('2d');
    const bandUsageChart = new Chart(bandctx, {
        type: 'line',
        data: {
            labels: labels,
            datasets: [
                {
                    label: 'Bandwidth Usage (%)',
                    data: bandwidthUsageData,
                    borderColor: 'rgb(70,189,61)',
                    backgroundColor: 'rgba(75, 192, 192, 0.2)',
                    borderWidth: 3,
                    fill: false,
                    tension: 0
                },
                {
                    label: 'Bandwidth Allocation (%)',
                    data: bandAllocationData,
                    borderColor: 'red',
                    backgroundColor: 'rgba(192, 75, 75, 0.2)',
                    borderWidth: 3,
                    fill: false,
                    tension: 0
                }
            ]
        },
        options: {
            title: {
                display: true,
                text: 'Bandwith Usage Data On Nodes',
                fontSize: 36,
                fontColor: 'black'
            },
            scales: {
                x: {
                    title: {
                        display: true,
                        text: 'Node ID',

                    }
                },
                y: {
                    title: {
                        display: true,
                        text: 'Usage (%)'
                    },
                    beginAtZero: true
                }
            }
        }
    });

    function webSocketInvoke() {
        console.log("WebSocket is supported by your Browser!");
        var ws = new WebSocket("ws://localhost:8082/", "echo-protocol");

        ws.onopen = function() {
            console.log("Connection created");
        };

        ws.onmessage = function(evt) {
            var received_msg = evt.data;
            var newData = JSON.parse(received_msg);
            console.log(newData)
            updateChart(cpuUsageChart, newData);
            updateChart(memUsageChart, newData);
            updateChart(bandUsageChart, newData);

        };

        ws.onclose = function() {
            console.log("Connection closed");
        };
    }

    function updateChart(chart, newData) {
        if (!chart.data.labels.includes(newData.nodeId)) {
            chart.data.labels.push(newData.nodeId);
        }

        const dataIndex = chart.data.labels.indexOf(newData.nodeId);
        if(chart === cpuUsageChart) {
            chart.data.datasets[0].data[dataIndex] = newData.cpuUsage;
            chart.data.datasets[1].data[dataIndex] = newData.cpuAllocated;
        }
        else if(chart === memUsageChart) {
            chart.data.datasets[0].data[dataIndex] = newData.memoryUsage;
            chart.data.datasets[1].data[dataIndex] = newData.memoryAllocated;
        }
        else if(chart === bandUsageChart) {
            chart.data.datasets[0].data[dataIndex] = newData.bandwidthUsage;
            chart.data.datasets[1].data[dataIndex] = newData.bandwidthAllocated;
        }
        chart.update();
    }

    webSocketInvoke();
    $('#RecPage').hide()
    // hideAndShowGraph("showRec", 1)
});
function hideAndShowGraph(option, networkId){
    if (option === "showRec"){
        $('#carouselExample').hide()
        $('#RecPage').show()
        $('#recTitle').html('Resource Allocation Recommendations for Network ID: ' + networkId)
    }else{
        $('#carouselExample').show()
        $('#RecPage').hide()

    }
}
function drillDownToNetwork(nodeID){

}