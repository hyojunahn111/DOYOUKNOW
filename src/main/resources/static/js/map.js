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
        {name: "100주년기념관", latlng: new kakao.maps.LatLng(37.642833983307035, 127.10528090160582),deptNames: "교무처", deptItmes: "기구1"},
        {name: "국제교육관", latlng: new kakao.maps.LatLng(37.64322349388041, 127.10546557413087),deptNames: "상담심리학과", deptItmes: "기구1"},
        {name: "신학관", latlng: new kakao.maps.LatLng(37.6423008811867, 127.10445868842582),deptNames: "신학과", deptItmes: "기구1"},
        {name: "스미스관", latlng: new kakao.maps.LatLng(37.64256461908935, 127.10423244703988),deptNames: "", deptItmes: "기구1"},
        {name: "사무엘관", latlng: new kakao.maps.LatLng(37.64322954325419, 127.10372349751437),deptNames: "사회복지학과", deptItmes: "기구1"},
        {name: "에덴관", latlng: new kakao.maps.LatLng(37.64305922646987, 127.10272899971714),deptNames: "", deptItmes: "기구1"},
        {name: "로뎀관", latlng: new kakao.maps.LatLng(37.643176909928926, 127.10208898132788),deptNames: "", deptItmes: "기구1"},
        {name: "브니엘관", latlng: new kakao.maps.LatLng(37.64360029089598, 127.10218303894692),deptNames: "", deptItmes: "기구1"},
        {name: "다목적관", latlng: new kakao.maps.LatLng(37.64366943237049, 127.10297061746341),deptNames: "", deptItmes: "기구1"},
        {name: "살렘관", latlng: new kakao.maps.LatLng(37.64440602274736, 127.10292914369208),deptNames: "", deptItmes: "기구1"},
        {name: "시온관", latlng: new kakao.maps.LatLng(37.64415260359344, 127.10423183320293),deptNames: "", deptItmes: "기구1"},
        {name: "음악관", latlng: new kakao.maps.LatLng(37.64366565158367, 127.10470704225811),deptNames: "음악학과", deptItmes: "기구1"},
        {name: "70주년기념관", latlng: new kakao.maps.LatLng(37.64397572316407, 127.10556861437944),deptNames: "", deptItmes: "기구1"},
        {name: "1실습관/창업보육센터", latlng: new kakao.maps.LatLng(37.64437225175103, 127.10546153287211),deptNames: "유아교육학과,\n컴퓨터학부,\nIT융합공학과", deptItmes: "기구1", imgSrc:"img/academyimg/one.png"},
        {name: "디자인관", latlng: new kakao.maps.LatLng(37.644488754493906, 127.10615854340683),deptNames: "아트앤디자인학과", deptItmes: "기구1"},
        {name: "실험동물실", latlng: new kakao.maps.LatLng(37.64476348784382, 127.1062325850367),deptNames: "", deptItmes: "기구1"},
        {name: "목공실습실", latlng: new kakao.maps.LatLng(37.64477220132507, 127.10656119265859),deptNames: "", deptItmes: "기구1"},
        {name: "온실", latlng: new kakao.maps.LatLng(37.64445220917211, 127.10671936702681),deptNames: "", deptItmes: "기구1"},
        {name: "아트엔디자인관", latlng: new kakao.maps.LatLng(37.64419146085434, 127.10612412797529),deptNames: "", deptItmes: "기구1", imgSrc:"img/academyimg/art.png"},
        {name: "중앙도서관", latlng: new kakao.maps.LatLng(37.6431459048178, 127.1065843633995),deptNames: "", deptItmes: "기구1", imgSrc:"img/academyimg/middle.png"},
        {name: "에스라관", latlng: new kakao.maps.LatLng(37.643618510363694, 127.10703826642198),deptNames: "글로벌한국학과,\n 한국기업", deptItmes: "기구1"},
        {name: "제1과학관", latlng: new kakao.maps.LatLng(37.64406006374351, 127.10695958468133),deptNames: "화학생명과학과,\n 화학과,\n 생명과학과", deptItmes: "기구1", imgSrc:"img/academyimg/onesc.png"},
        {name: "제2과학관", latlng: new kakao.maps.LatLng(37.643590950020915, 127.10762175740494),deptNames: "동물생명자원학과,\n 동물과학부", deptItmes: "기구1", imgSrc:"img/academyimg/twosc.png"},
        {name: "식품영약학과 실습실", latlng: new kakao.maps.LatLng(37.64377981985591, 127.10799027824285),deptNames: "", deptItmes: "기구1"},
        {name: "학생생활상담센터", latlng: new kakao.maps.LatLng(37.643770597185814, 127.10822254459205),deptNames: "", deptItmes: "기구1"},
        {name: "평생교육원/유치원", latlng: new kakao.maps.LatLng(37.6431218793443, 127.10823293408282),deptNames: "", deptItmes: "기구1"},
        {name: "제3과학관", latlng: new kakao.maps.LatLng(37.642842168511066, 127.10867442072474),deptNames: "간호학과,\n 물리치료학과,\n 약학과", deptItmes: "기구1"},
        {name: "뉴스타트센터", latlng: new kakao.maps.LatLng(37.64182838536834, 127.10886556107491),deptNames: "", deptItmes: "기구1"},
        {name: "다니엘관", latlng: new kakao.maps.LatLng(37.642563695355015, 127.1077675723511),deptNames: "스미스교양대학", deptItmes: "기구1"},
        {name: "요한관", latlng: new kakao.maps.LatLng(37.642270673938604, 127.10798809359333),deptNames: "", deptItmes: "기구1"},
        {name: "박물관", latlng: new kakao.maps.LatLng(37.642171881642135, 127.10764237112055),deptNames: "", deptItmes: "기구1"},
        {name: "바울관", latlng: new kakao.maps.LatLng(37.64273097304791, 127.10711347420661),deptNames: "중국어학과, 일본어학과, 경영학과, 교목처, 교직", deptItmes: "기구1"},
        {name: "대학일자리본부/학생회관", latlng: new kakao.maps.LatLng(37.641361866714746, 127.1066837876751),deptNames: "대학일자리본부", deptItmes: "기구1", imgSrc:"img/academyimg/job.png"}
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
            '                <img src="' + (locations[i].imgSrc || 'img/default.png') + '" width="110" height="90">' +
            '           </div>' +
            '            <div class="desc">' +
            '                <div>' + "소속과 : " + '<br>' + '<p>' + locations[i].deptNames + '</p>' + '</div>' +
            '                <div>' + "소속기구 : " + '<br>' + '<p>' + locations[i].deptItmes + '</p>' +'</div>' +
            '            </div>' +
            '        </div>' +
            '    </div>' +
            // '    <hr class="info_hr">' +
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
            var buildingDeptMatch = locations[i].deptNames.toLowerCase().includes(keyword);

            if (buildingMatch || buildingDeptMatch) {
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

        // resetMap();
    }

    /* 밑의 함수가 오버레이에서 x 버튼을 눌렀을 때 초기 화면으로 돌아가게함*/
    // function resetMap() {
    //     // 모든 마커를 맵에 다시 표시
    //     for (var i = 0; i < markers.length; i++) {
    //         markers[i].setMap(map);
    //     }
    //
    //     // 모든 오버레이를 맵에서 제거
    //     for (var i = 0; i < overlays.length; i++) {
    //         overlays[i].setMap(null);
    //     }
    //
    //     // 맵의 중심과 레벨을 초기 상태로 설정
    //     map.setCenter(new kakao.maps.LatLng(37.642785, 127.105220));
    //     map.setLevel(3);
    //
    //     // 목록 초기화
    //     var placesList = document.getElementById('placesList');
    //     placesList.innerHTML = '';
    //
    //     // 초기화 알림 (필요시 사용)
    //     // alert('초기화 되었습니다.');
    // }

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
                        '<button class="link">' + '<a href="http://localhost:8080/board/dept/' + dept.seq + '">홈페이지로 이동</a>' + '</button>' +
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
        let findlocArr = allData.filter(dept => dept.loc.includes(this.value));
        console.log("이것은 건물이름", findlocArr);
        console.log("이것은 학과이름", findArr);

        if (!findArr.length || !findlocArr.length) {
            // return;

        let combinedArr = [...new Set([...findArr, ...findlocArr])];
        console.log("야 이거 뭐냐", combinedArr)

        combinedArr.forEach(item => {
            myContent.innerHTML += '<div>' + item.name + ' - '+ '<br>' + item.loc + '</div>';
        });
        myContent.style.display = "block";
        }
    });
    fetchDeptData();
});
