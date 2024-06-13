window.open("/popup", 'popup', 'width=505, height=505, status=no, scrollbars= 0, toolbar=0, menubar=no, left = 100, right = 0, top=100')
window.open("/popup", 'popup2', 'width=505, height=505, status=no, scrollbars= 0, toolbar=0, menubar=no, left = 180, right = 0, top=180')

// window.open('/popup', 'width=500, height=650, status=no, scrollbars= 0, toolbar=0, menubar=no');

// var noticeCookie = getCookie("name");  // 쿠기 가져오기
// if (noticeCookie != "value"){
//     // 팝업창 띄우기
//     window.open('/map', 'width=500, height=650, status=no, scrollbars= 0, toolbar=0, menubar=no');
// }
//
// function setCookie(name, value)
// {
//     var todayDate = new Date();
//     todayDate.setHours( 24 );
//     document.cookie = name+ "=" + escape( value ) + "; path=/; expires=" +   todayDate.toGMTString() + ";";
// }
//
// function closeWin()
// {
//     if ($("#option").is(":checked") )
//     {
//         setCookie("name", "value");
//         window.close();
//     }
// }
//
// function getCookie(name) {
//     var Found = false
//     var start, end
//     var i = 0
//
//     while(i <= document.cookie.length) {
//         start = i
//         end = start + name.length
//
//         if(document.cookie.substring(start, end) == name) {
//             Found = true
//             break
//         }
//         i++
//     }
//
//     if(Found == true) {
//         start = end + 1
//         end = document.cookie.indexOf(";", start)
//         if(end < start)
//             end = document.cookie.length
//         return document.cookie.substring(start, end)
//     }
//     return ""
// }