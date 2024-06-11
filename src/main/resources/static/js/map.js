document.addEventListener("DOMContentLoaded", function () {
    const container = document.getElementById('map');
    let options = {
        center: new kakao.maps.LatLng(37.642785, 127.105220),
        level: 3
    };
    let map = new kakao.maps.Map(container, options);

    var imageSrc = 'img/location_marker.png',
        imageSize = new kakao.maps.Size(40, 40),
        imageOption = {offset: new kakao.maps.Point(20, 40)};

    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);

    var infowindow = new kakao.maps.InfoWindow({zIndex: 1});

    var locations = [
        {name: "100주년기념관", latlng: new kakao.maps.LatLng(37.642833983307035, 127.10528090160582)},
        {name: "국제교육관", latlng: new kakao.maps.LatLng(37.64322349388041, 127.10546557413087)},
        {name: "음악관", latlng: new kakao.maps.LatLng(37.64366565158367, 127.10470704225811)},
        {name: "제1과학관", latlng: new kakao.maps.LatLng(37.64406006374351, 127.10695958468133)
        }
    ];

    var overlays = [];
    var markers = [];

    for (var i = 0; i < locations.length; i++) {
        var marker = new kakao.maps.Marker({
            map: map,
            position: locations[i].latlng,
            title: locations[i].name,
            image: markerImage
        });

        markers.push(marker);

        var content = '<div class="wrap">' +
            '    <div class="info">' +
            '        <div class="title">' +
            '            ' + locations[i].name +
            '            <div class="close" onclick="closeOverlay(' + i + ')" title="닫기"></div>' +
            '        </div>' +
            '        <div class="body">' +
            '            <div class="img">' +
            '                <img src="' + (locations[i].imgSrc || 'img/default.png') + '" width="73" height="70">' +
            '           </div>' +
            '            <div class="desc">' +
            '            </div>' +
            '        </div>' +
            '    </div>' +
            '    <hr class="info_hr">' +
            '</div>';

        var overlay = new kakao.maps.CustomOverlay({
            content: content,
            position: marker.getPosition()
        });

        overlay.setMap(null);
        overlays.push(overlay);

        kakao.maps.event.addListener(marker, 'click', (function (overlay, map) {
            return function () {
                for (var j = 0; j < overlays.length; j++) {
                    overlays[j].setMap(null);
                }
                overlay.setMap(map);
            };
        })(overlay, map));
    }

    function searchPlaces(event) {
        event.preventDefault();

        var keyword = document.getElementById('keyword').value.trim().toLowerCase();
        console.log("검색 키워드:", keyword);    // 뭐 검색하는지 확인

        if (!keyword) {
            alert('검색하실 학과나 건물을 입력해주세요.');
            return false;
        }

        for (var j = 0; j < overlays.length; j++) {
            overlays[j].setMap(null);
            markers[j].setMap(null);
        }

        var found = false;
        var placesList = document.getElementById('placesList');
        placesList.innerHTML = '';

        for (var i = 0; i < locations.length; i++) {
            var buildingMatch = locations[i].name.toLowerCase().includes(keyword);

            if (buildingMatch) {
                overlays[i].setMap(map);
                markers[i].setMap(map);
                map.setCenter(locations[i].latlng);
                map.setLevel(1);
                found = true;
                break;
            }

            if (found) {
                break;
            }
        }

        if (!found) {
            alert('일치하는 장소가 없습니다.');
            for (var j = 0; j < overlays.length; j++) {
                markers[j].setMap(map);
            }
        }
    }


    window.closeOverlay = function (index) {
        overlays[index].setMap(null);
    }

    document.getElementById('locDetailForm').addEventListener('submit', searchPlaces);

    document.getElementById('resetButton').addEventListener('click', function() {
        for (var i = 0; i < markers.length; i++) {
            markers[i].setMap(map);
        }
        for (var i = 0; i < overlays.length; i++) {
            overlays[i].setMap(null);
        }
        map.setCenter(new kakao.maps.LatLng(37.642785, 127.105220));
        map.setLevel(3);

        // 목록 초기화
        var placesList = document.getElementById('placesList');
        placesList.innerHTML = '';

        /*alert('초기화 되었습니다.')*/ //사용시에 알람창 확인을 눌러야지만 초기화가 됨
    });
});

$(document).ready(function() {
    var allData = [];  // 전역 변수로 데이터 저장

    function fetchDeptData(locDetail) {
        $.ajax({
            url: '/mapData',
            type: 'GET',
            data: { locDetail: locDetail },
            success: function(data) {
                console.log('DeptInfo:', data);
                allData = data;  // 전역 변수에 데이터 저장
                var placesList = $('#placesList');
                placesList.empty();
                data.forEach(function(dept) {
                    placesList.append(
                        '<li>' + '<h3>' + dept.name + '</h3>' +
                        '<p class="deptphone">' + dept.phone + '</p>' +
                        '<p class="locdetail">' + dept.locDetail + '</p>' +
                        '<p class="intro">' + dept.intro + '</p>' +
                        '<button class="link">' + "링크입니다" + '</button>' +
                        '</li>' +
                        '<hr>'
                    );
                });
            },
            error: function(error) {
                console.error('데이터 에러 발생:', error);
            }
        });
    }

    $('#locDetailForm').on('submit', function(event) {
        event.preventDefault();
        var locDetail = $('#keyword').val();
        fetchDeptData(locDetail);
    });

    $('#resetButton').on('click', function() {
        $('#keyword').val('');
        fetchDeptData();
    });

    const myContent = document.querySelector("#content");
    const keyword = document.querySelector("#keyword");

    keyword.addEventListener("keyup", function () {
        if (!this.value.trim()) {
            myContent.innerHTML = "";
            myContent.style.display = "none";
            return;
        }
        console.log("확인:", this.value);
        myContent.innerHTML = "";
        myContent.style.display = "none";

        let findArr = allData.filter(dept => dept.name.includes(this.value));

        if (!findArr.length) {
            return;
        }
        findArr.forEach(item => {
            myContent.innerHTML += '<div>' + item.name + '</div>';
        });
        myContent.style.display = "block";
    });
    fetchDeptData();
});
