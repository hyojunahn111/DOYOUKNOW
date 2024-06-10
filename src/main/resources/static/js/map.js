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
        {name: "100주년기념관", latlng: new kakao.maps.LatLng(37.642833983307035, 127.10528090160582),
            departments: []
        },
        {name: "국제교육관", latlng: new kakao.maps.LatLng(37.64322349388041, 127.10546557413087),
            departments: []
        },
        {name: "음악관", latlng: new kakao.maps.LatLng(37.64366565158367, 127.10470704225811),
            departments: [
                {
                    deptName: "음악학과",
                    deptPhone: "02-3399-1818",
                    locDetail: "108호",
                    intro: "간단한설명21",
                    link: "이것은 링크입니다."
                }
            ]
        },
        {name: "제1과학관", latlng: new kakao.maps.LatLng(37.64406006374351, 127.10695958468133),
            departments: [
                {
                    deptName: "화학생명과학과",
                    deptPhone: "02-3399-1720",
                    locDetail: "204호",
                    intro: "간단한 설명9",
                    link: "이것은 링크입니다."
                },
                {
                    deptName: "컴퓨터학부",
                    deptPhone: "02-3399-1790",
                    locDetail: "401호",
                    intro: "간단한 설명14",
                    link: "이것은 링크입니다."
                }
            ]
        }
        // {name: "박물관", latlng: new kakao.maps.LatLng(37.642171881642135, 127.10764237112055), imgSrc: 'img/default.png', departments: [{ deptName: '상담심리학과', deptPhone: '02-3399-1679', locDetail: '122호', intro: '간단한 설명6', link: 'http://localhost:8080/hundredhall' }]}
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
        console.log("검색 키워드:", keyword);    //뭐 검색하는지 확인

        if (!keyword) {
            alert('검색하실 학과나 건물을 입력해주세요.');
            return false;
        }

        for (var j = 0; j < overlays.length; j++) {
            overlays[j].setMap(null);
            markers[j].setMap(null);
        }

        var found = false;
        for (var i = 0; i < locations.length; i++) {
            console.log("불러오는 단어 : " + locations[i].name.toLowerCase(), "내가 입력한 단어 : " + keyword);
            if (locations[i].name.toLowerCase() === keyword) {
                overlays[i].setMap(map);
                markers[i].setMap(map);
                map.setCenter(locations[i].latlng);
                found = true;

                //정보 표시 div
                var placesList = document.getElementById('placesList');
                placesList.innerHTML = '';

                for (var j = 0; j < locations[i].departments.length; j++) {
                    placesList.innerHTML += `
                        <li>
                            <h3>${locations[i].departments[j].deptName}</h3>
                            <p class="deptphone">${locations[i].departments[j].deptPhone}</p>
                            <p class="loc">${locations[i].name}&nbsp${locations[i].departments[j].locDetail}</p>
                            <p class="intro">${locations[i].departments[j].intro}</p>
                            <p class="link">${locations[i].departments[j].link}</p>
                        </li>
                    `;
                }

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

        // 목록 초기화
        var placesList = document.getElementById('placesList');
        placesList.innerHTML = '';

        alert('초기화 되었습니다.')
    });
});