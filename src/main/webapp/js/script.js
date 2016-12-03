
// var button = document.createElement("button");
// button.innerHTML = "Add Node";

// var body = document.getElementsByTagName("body")[0];
// body.appendChild(button);

function addNode() {

  var elem = document.getElementById('canvas'),
        elemLeft = elem.offsetLeft,
        elemTop = elem.offsetTop,
        ctx = elem.getContext('2d')

  var nodeButton = document.getElementById('nodeButton');
  var nodeLabel = document.getElementById('nodeLabel').value;

  console.log(nodeLabel)

  nodeLabel = "";

    nodeButton.disabled = true;
    console.log("sth clicked");
    once();
  
    function once() {
        nodeButton.removeEventListener("click", arguments.callee);
        drawCircle();
    }
  
    function drawCircle() {
        elem.addEventListener('click', function(event) {
        var x = event.pageX - elemLeft,
            y = event.pageY - elemTop;
  
        ctx.beginPath();
        ctx.arc(x,y,20,0,2*Math.PI);
        ctx.stroke();
        
        console.log(x, y);
        elem.removeEventListener("click", arguments.callee);
        nodeButton.disabled = false;
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

      
// function draftCircle() {

//   console.log("draftCircle()")

//   document.addEventListener("DOMContentLoaded", init, false);

//   function init() {
    
//     var canvas = document.getElementById("canvas");
//     canvas.addEventListener("mousedown", getPosition, false);

//   }

//   function getPosition(event) {
    
//     var x = new Number();
//     var y = new Number();
//     var canvas = document.getElementById("canvas");
//     var ctx = canvas.getContext("2d");
//     ctx.beginPath();

//     if (event.x != undefined && event.y != undefined)
//     {
//       x = event.x;
//       y = event.y;
//     }
//     else // Firefox method to get the position
//     {
//       x = event.clientX + document.body.scrollLeft +
//           document.documentElement.scrollLeft;
//       y = event.clientY + document.body.scrollTop +
//           document.documentElement.scrollTop;
//     }

//     x -= canvas.offsetLeft;
//     y -= canvas.offsetTop;


//     ctx.arc(x,y,10,0,2*Math.PI);
//     ctx.stroke();

//     console.log('x: ' + x + '  y: ' + y);

//   }
// }

