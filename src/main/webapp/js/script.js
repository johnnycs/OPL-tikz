
var svg = d3.select("#chart").append("svg")
                             .attr("width", 640)
                             .attr("height", 360)
                             .style("border", "1px black solid");

var graph = {"nodes": [], "edges": []};
var nodeId = 0;

function addNode() {

  var singleton = {id:null, x:null, y:null, title:null};
  var clicked = false;
  var nodeButton = document.getElementById('nodeButton');
  var nodeLabel = document.getElementById('nodeLabel').value;

  singleton.title = nodeLabel;
  singleton.id = nodeId;
  nodeId ++;

  console.log(nodeLabel)
  if (nodeLabel == "") {
    console.log(nodeId)
    nodeLabel = nodeId;
    singleton.title = nodeId.toString()
    console.log(nodeLabel)
  }

  nodeButton.disabled = true;
  console.log("sth clicked");
  doCircle();
  document.getElementById('nodeLabel').value = "";

  function doCircle() {

    function drawCircle(x, y) {
      console.log('Drawing circle at', x, y);
      svg.append("circle")
          // .attr('class', 'click-circle')
          .attr("cx", x)
          .attr("cy", y)
          .attr("r", 23)
          .attr("stroke","black")
          .style("fill", "none");
          
      svg.append("text")
          .attr("x", x - 6.5)
          .attr("y", y + 5)
          .text(nodeLabel)
          .attr("fill", "black");

      nodeButton.disabled = false;
    }

    svg.on('click', function() {

      if (!clicked) {
        var coords = d3.mouse(this);
        // console.log(coords);

        drawCircle(coords[0], coords[1]);
        clicked = true;

        singleton.x = coords[0];
        singleton.y = coords[1];

        graph.nodes.push(singleton)
      }
    });
  }
}

function addEdge() {

  var edgeButton = document.getElementById('edgeButton');  
  edgeButton.disabled = true;

  var edge = {"source":null, "target":null};

  // var edgeButton = document.getElementById('edgeButton');
  var edgeFrom = document.getElementById('edgeFrom').value;
  var edgeTo = document.getElementById('edgeTo').value;
  var x1, y1, xy1_id, x2, y2, xy2_id = null;


  console.log(edgeFrom.length)
  console.log(edgeTo.length)

  if ((edgeFrom.length != 0) && (edgeTo.length != 0)) {
    edgeButton.disabled = false;
  }

  for(var i = 0; i < graph.nodes.length; i++) {

    if (graph.nodes[i].title == edgeFrom) {
      console.log("from",graph.nodes[i])
      x1 = graph.nodes[i].x;
      y1 = graph.nodes[i].y;
      xy1_id = graph.nodes[i].id;
    }

    if (graph.nodes[i].title == edgeTo) {
      console.log("to",graph.nodes[i])
      x2 = graph.nodes[i].x
      y2 = graph.nodes[i].y
      xy2_id = graph.nodes[i].id;
    }

    // else {
    //   edgeButton.disabled = true;
    // }
  }

  // math stuff
  var refX = 23 + (15 * 2)

  svg.append("svg:defs").append("svg:marker")
    .attr("id", "triangle")
    .attr("refX", refX)
    .attr("refY", 6)
    .attr("markerWidth", 15)
    .attr("markerHeight", 15)
    .attr("orient", "auto")
    .append("path")
    .attr("d", "M 0 0 12 6 0 12 3 6")
    .style("fill", "black");

  svg.append("line")
    .attr("x1",  x1)
    .attr("y1", y1)
    .attr("x2", x2)
    .attr("y2", y2)

    .attr("stroke-width", 1)
    .attr("stroke", "black")
    .attr("marker-end", "url(#triangle)");

    edge.source = xy1_id;
    edge.target = xy2_id;

    graph.edges.push(edge)
    document.getElementById('edgeFrom').value = "";
    document.getElementById('edgeTo').value = "";
    console.log(graph)

}

///////////////////////////////////////////////////////////////////////////////////////////

function magic() {

  function onSuc(data){
    console.log(data)
  }

  function onFai(){}

  $.ajax({
    dataType: "json",
    url: "/graph",
    data: null,
    success: onSuc,
    fail: onFai
  });

}

function downloadTikz() {

  $.ajax({
            type: "POST",
            // async: false,
            url: '/toTikz',
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify(graph),
            complete: function(data, jqXHR){
                // console.log(jqXHR.status);
                window.location.href= "http://localhost:8080/downloadTikz";
            }
        });

}


function clearGraph() {

//   svg.selectAll('*').remove();
//   graph.nodes.splice(0,graph.nodes.length)
//   graph.edges.splice(0,graph.edges.length)
//   console.log(graph)
//   console.log("clear")

}

      