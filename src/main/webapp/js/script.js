      
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



var button = document.createElement("button");
button.innerHTML = "Add Node";

var body = document.getElementsByTagName("body")[0];
body.appendChild(button);

var elem = document.getElementById('canvas'),
    elemLeft = elem.offsetLeft,
    elemTop = elem.offsetTop,
    ctx = elem.getContext('2d')
    ctx.beginPath();

button.addEventListener("click", function() {
    button.disabled = true;
    console.log("sth clicked");
    once();
});

function once() {
    button.removeEventListener("click", arguments.callee);
    drawCircle();
}

function drawCircle() {
    elem.addEventListener('click', function(event) {
    var x = event.pageX - elemLeft,
        y = event.pageY - elemTop;

    ctx.arc(x,y,10,0,2*Math.PI);
    ctx.stroke();
    
    console.log(x, y);
    elem.removeEventListener("click", arguments.callee);
    button.disabled = false;
    });
}



