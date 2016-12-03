
var svg = d3.select("#chart").append("svg")
                                 .attr("width", 640)
                                 .attr("height", 360)
                                 .style("border", "1px black solid");

function addNode() {

  var clicked = false;
  var nodeButton = document.getElementById('nodeButton');
  var nodeLabel = document.getElementById('nodeLabel').value;

  console.log(nodeLabel)

    nodeButton.disabled = true;
    console.log("sth clicked");
    doCircle();

    function doCircle() {

      function drawCircle(x, y) {

          console.log('Drawing circle at', x, y);
          svg.append("circle")
              .attr('class', 'click-circle')
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
              console.log(coords);
              drawCircle(coords[0], coords[1]);
              clicked = true;
            }

        });

    }

}


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

function magic2() {
  data = {
    "name": "john",
    "girlfriend": "bow",
    "gik": ["bossy", "taewon"]
  }

  $.ajax({
    type: "POST",
    dataType: "json",
    url: "/john",
    data: data,
  });

}


// function magic(){
//   var jqxhr = $.ajax( "/graph" )
//     .done(function() {
//       alert( "success" );
//     })
//     .fail(function() {
//       alert( "error" );
//     })
// }

// function hello(){
//   alert('hello')
// }


// var button = document.createElement("button");
// button.innerHTML = "Add Node";

// var body = document.getElementsByTagName("body")[0];
// body.appendChild(button);

// function addNode() {

//   var elem = document.getElementById('canvas'),
//         elemLeft = elem.offsetLeft,
//         elemTop = elem.offsetTop,
//         ctx = elem.getContext('2d')

//   var nodeButton = document.getElementById('nodeButton');
//   var nodeLabel = document.getElementById('nodeLabel').value;

//   console.log(nodeLabel)

//     nodeButton.disabled = true;
//     console.log("sth clicked");
//     once();
  
//     function once() {
//         nodeButton.removeEventListener("click", arguments.callee);
//         drawCircle();
//     }
  
//     function drawCircle() {
//         elem.addEventListener('click', function(event) {
//         var x = event.pageX - elemLeft,
//             y = event.pageY - elemTop;
  
//         ctx.beginPath();
//         ctx.arc(x,y,20,0,2*Math.PI);
//         ctx.stroke();
        
//         console.log(x, y);
//         elem.removeEventListener("click", arguments.callee);
//         nodeButton.disabled = false;
//         });
//     }
// }
      