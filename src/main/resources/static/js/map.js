document.addEventListener("DOMContentLoaded", function () {
    const container = document.getElementById('map');
    let options = {
        center: new kakao.maps.LatLng(37.642785, 127.105220),  // 지도의 중심좌표.
        level: 3  // 지도의 레벨(확대, 축소 정도)
    };
    let map = new kakao.maps.Map(container, options);

    // 마커 이미지 설정
    var imageSrc = 'img/location_marker.png',  // 마커 이미지의 주소
        imageSize = new kakao.maps.Size(40, 40),  // 마커 이미지의 크기
        imageOption = {offset: new kakao.maps.Point(20, 40)};  // 마커 이미지의 옵션

    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);

    var locations = [
        {name: "100주년기념관", latlng: new kakao.maps.LatLng(37.642833983307035, 127.10528090160582), imgSrc: 'img/default.png', deptname: '교무처', phone: '02-3399-3358', loc: '백주년기념관', locDetail : '108호', intro : '외래강사문의', link:'http://localhost:8080/hundredhall'},
        {name: "국제교육관", latlng: new kakao.maps.LatLng(37.64322349388041, 127.10546557413087),imgSrc: 'img/default.png', deptname: '상담심리학과', phone: '02-3399-1679', loc: '국제교육관', locDetail : '122호', intro : '간단한 설명6', link:'http://localhost:8080/internationaleducation'},
        {name: "신학관", latlng: new kakao.maps.LatLng(37.6423008811867, 127.10445868842582),imgSrc: 'img/default.png', deptname: '신학과', phone: '02-3399-1513', loc: '신학관', locDetail : '108호', intro : '간단한 설명입니다', link:'http://localhost:8080/theology'},
        {name: "스미스관", latlng: new kakao.maps.LatLng(37.64256461908935, 127.10423244703988),imgSrc: 'img/default.png', deptname: '상담심리학과', phone: '02-3399-1679', loc: '국제교육관', locDetail : '122호', intro : '간단한 설명6', link:'http://localhost:8080/hundredhall'},
        {name: "사무엘관", latlng: new kakao.maps.LatLng(37.64322954325419, 127.10372349751437),imgSrc: 'img/default.png', deptname: '상담심리학과', phone: '02-3399-1679', loc: '국제교육관', locDetail : '122호', intro : '간단한 설명6', link:'http://localhost:8080/hundredhall'},
        {name: "에덴관", latlng: new kakao.maps.LatLng(37.64305922646987, 127.10272899971714),imgSrc: 'img/default.png', deptname: '상담심리학과', phone: '02-3399-1679', loc: '국제교육관', locDetail : '122호', intro : '간단한 설명6', link:'http://localhost:8080/hundredhall'},
        {name: "로뎀관", latlng: new kakao.maps.LatLng(37.643176909928926, 127.10208898132788),imgSrc: 'img/default.png', deptname: '상담심리학과', phone: '02-3399-1679', loc: '국제교육관', locDetail : '122호', intro : '간단한 설명6', link:'http://localhost:8080/hundredhall'},
        {name: "브니엘관", latlng: new kakao.maps.LatLng(37.64360029089598, 127.10218303894692),imgSrc: 'img/default.png', deptname: '상담심리학과', phone: '02-3399-1679', loc: '국제교육관', locDetail : '122호', intro : '간단한 설명6', link:'http://localhost:8080/hundredhall'},
        {name: "다목적관", latlng: new kakao.maps.LatLng(37.64366943237049, 127.10297061746341),imgSrc: 'img/default.png', deptname: '상담심리학과', phone: '02-3399-1679', loc: '국제교육관', locDetail : '122호', intro : '간단한 설명6', link:'http://localhost:8080/hundredhall'},
        {name: "살렘관", latlng: new kakao.maps.LatLng(37.64440602274736, 127.10292914369208),imgSrc: 'img/default.png', deptname: '상담심리학과', phone: '02-3399-1679', loc: '국제교육관', locDetail : '122호', intro : '간단한 설명6', link:'http://localhost:8080/hundredhall'},
        {name: "시온관", latlng: new kakao.maps.LatLng(37.64415260359344, 127.10423183320293),imgSrc: 'img/default.png', deptname: '상담심리학과', phone: '02-3399-1679', loc: '국제교육관', locDetail : '122호', intro : '간단한 설명6', link:'http://localhost:8080/hundredhall'},
        {name: "음악관", latlng: new kakao.maps.LatLng(37.64366565158367, 127.10470704225811),imgSrc: 'img/default.png', deptname: '상담심리학과', phone: '02-3399-1679', loc: '국제교육관', locDetail : '122호', intro : '간단한 설명6', link:'http://localhost:8080/hundredhall'},
        {name: "70주년기념관", latlng: new kakao.maps.LatLng(37.64397572316407, 127.10556861437944),imgSrc: 'img/default.png', deptname: '상담심리학과', phone: '02-3399-1679', loc: '국제교육관', locDetail : '122호', intro : '간단한 설명6', link:'http://localhost:8080/hundredhall'},
        {name: "1실습관/창업보육센터", latlng: new kakao.maps.LatLng(37.64437225175103, 127.10546153287211),imgSrc: 'img/default.png', deptname: '상담심리학과', phone: '02-3399-1679', loc: '국제교육관', locDetail : '122호', intro : '간단한 설명6', link:'http://localhost:8080/hundredhall'},
        {name: "디자인관", latlng: new kakao.maps.LatLng(37.644488754493906, 127.10615854340683),imgSrc: 'img/default.png', deptname: '상담심리학과', phone: '02-3399-1679', loc: '국제교육관', locDetail : '122호', intro : '간단한 설명6', link:'http://localhost:8080/hundredhall'},
        {name: "실험동물실", latlng: new kakao.maps.LatLng(37.64476348784382, 127.1062325850367),imgSrc: 'img/default.png', deptname: '상담심리학과', phone: '02-3399-1679', loc: '국제교육관', locDetail : '122호', intro : '간단한 설명6', link:'http://localhost:8080/hundredhall'},
        {name: "목공실습실", latlng: new kakao.maps.LatLng(37.64477220132507, 127.10656119265859),imgSrc: 'img/default.png', deptname: '상담심리학과', phone: '02-3399-1679', loc: '국제교육관', locDetail : '122호', intro : '간단한 설명6', link:'http://localhost:8080/hundredhall'},
        {name: "온실", latlng: new kakao.maps.LatLng(37.64445220917211, 127.10671936702681),imgSrc: 'img/default.png', deptname: '상담심리학과', phone: '02-3399-1679', loc: '국제교육관', locDetail : '122호', intro : '간단한 설명6', link:'http://localhost:8080/hundredhall'},
        {name: "아트엔디자인관", latlng: new kakao.maps.LatLng(37.64419146085434, 127.10612412797529),imgSrc: 'img/default.png', deptname: '상담심리학과', phone: '02-3399-1679', loc: '국제교육관', locDetail : '122호', intro : '간단한 설명6', link:'http://localhost:8080/hundredhall'},
        {name: "중앙도서관", latlng: new kakao.maps.LatLng(37.6431459048178, 127.1065843633995),imgSrc: 'img/default.png', deptname: '상담심리학과', phone: '02-3399-1679', loc: '국제교육관', locDetail : '122호', intro : '간단한 설명6', link:'http://localhost:8080/hundredhall'},
        {name: "에스라관", latlng: new kakao.maps.LatLng(37.643618510363694, 127.10703826642198),imgSrc: 'img/default.png', deptname: '상담심리학과', phone: '02-3399-1679', loc: '국제교육관', locDetail : '122호', intro : '간단한 설명6', link:'http://localhost:8080/hundredhall'},
        {name: "제1과학관", latlng: new kakao.maps.LatLng(37.64406006374351, 127.10695958468133),imgSrc: 'img/default.png', deptname: '상담심리학과', phone: '02-3399-1679', loc: '국제교육관', locDetail : '122호', intro : '간단한 설명6', link:'http://localhost:8080/hundredhall'},
        {name: "제2과학관", latlng: new kakao.maps.LatLng(37.643590950020915, 127.10762175740494),imgSrc: 'img/default.png', deptname: '상담심리학과', phone: '02-3399-1679', loc: '국제교육관', locDetail : '122호', intro : '간단한 설명6', link:'http://localhost:8080/hundredhall'},
        {name: "식품영약학과 실습실", latlng: new kakao.maps.LatLng(37.64377981985591, 127.10799027824285),imgSrc: 'img/default.png', deptname: '상담심리학과', phone: '02-3399-1679', loc: '국제교육관', locDetail : '122호', intro : '간단한 설명6', link:'http://localhost:8080/hundredhall'},
        {name: "학생생활상담센터", latlng: new kakao.maps.LatLng(37.643770597185814, 127.10822254459205),imgSrc: 'img/default.png', deptname: '상담심리학과', phone: '02-3399-1679', loc: '국제교육관', locDetail : '122호', intro : '간단한 설명6', link:'http://localhost:8080/hundredhall'},
        {name: "평생교육원/유치원", latlng: new kakao.maps.LatLng(37.6431218793443, 127.10823293408282),imgSrc: 'img/default.png', deptname: '상담심리학과', phone: '02-3399-1679', loc: '국제교육관', locDetail : '122호', intro : '간단한 설명6', link:'http://localhost:8080/hundredhall'},
        {name: "제3과학관", latlng: new kakao.maps.LatLng(37.642842168511066, 127.10867442072474),imgSrc: 'img/default.png', deptname: '상담심리학과', phone: '02-3399-1679', loc: '국제교육관', locDetail : '122호', intro : '간단한 설명6', link:'http://localhost:8080/hundredhall'},
        {name: "뉴스타트센터", latlng: new kakao.maps.LatLng(37.64182838536834, 127.10886556107491),imgSrc: 'img/default.png', deptname: '상담심리학과', phone: '02-3399-1679', loc: '국제교육관', locDetail : '122호', intro : '간단한 설명6', link:'http://localhost:8080/hundredhall'},
        {name: "다니엘관", latlng: new kakao.maps.LatLng(37.642563695355015, 127.1077675723511),imgSrc: 'img/default.png', deptname: '상담심리학과', phone: '02-3399-1679', loc: '국제교육관', locDetail : '122호', intro : '간단한 설명6', link:'http://localhost:8080/hundredhall'},
        {name: "요한관", latlng: new kakao.maps.LatLng(37.642270673938604, 127.10798809359333),imgSrc: 'img/default.png', deptname: '상담심리학과', phone: '02-3399-1679', loc: '국제교육관', locDetail : '122호', intro : '간단한 설명6', link:'http://localhost:8080/hundredhall'},
        {name: "박물관", latlng: new kakao.maps.LatLng(37.642171881642135, 127.10764237112055),imgSrc: 'img/default.png', deptname: '상담심리학과', phone: '02-3399-1679', loc: '국제교육관', locDetail : '122호', intro : '간단한 설명6', link:'http://localhost:8080/hundredhall'},
        {name: "대학일자리본부/학생회관", latlng: new kakao.maps.LatLng(37.641361866714746, 127.1066837876751),imgSrc: 'img/default.png', deptname: '상담심리학과', phone: '02-3399-1679', loc: '국제교육관', locDetail : '122호', intro : '간단한 설명6', link:'http://localhost:8080/hundredhall'}
    ];

    var overlays = [];

    for (var i = 0; i < locations.length; i++) {
        var marker = new kakao.maps.Marker({
            map: map,  // 마커 표시할 지도
            position: locations[i].latlng,  // 마커 표시할 위치
            title: locations[i].name,  // 마커 타이틀
            image: markerImage  // 마커 이미지
        });

        // 커스텀 오버레이 내용
        var content = '<div class="wrap">' +
            '    <div class="info">' +
            '        <div class="title">' +
            '            ' + locations[i].name +  // 장소 이름
            '            <div class="close" onclick="closeOverlay(' + i + ')" title="닫기"></div>' +
            '        </div>' +
            '        <div class="body">' +
            '            <div class="img">' +
            '                <img src="' + locations[i].imgSrc + '" width="73" height="70">' +
            '           </div>' +
            '            <div class="desc">' +
            '                <div class="dept">' + locations[i].deptname + '</div>' +
            '                <div class="infro dept">' + locations[i].phone + '</div>' +
            '                <div class="infro dept">' + locations[i].loc + ' ' + locations[i].locDetail + '</div>' +
            '                <div class="infro dept">' + locations[i].intro + '</div>' +
            '                <div><a href="' + locations[i].link + '" target="_blank" class="link">이동하기</a></div>' +
            '            </div>' +
            '        </div>' +
            '    </div>' +
            '</div>';

        // 커스텀 오버레이 생성
        var overlay = new kakao.maps.CustomOverlay({
            content: content,
            position: marker.getPosition()
        });

        overlay.setMap(null);  // 초기에는 오버레이를 숨기기
        overlays.push(overlay);

        // 마커 클릭 이벤트 추가
        kakao.maps.event.addListener(marker, 'click', (function (overlay, map) {
            return function () {
                // 모든 오버레이를 숨기기
                for (var j = 0; j < overlays.length; j++) {
                    overlays[j].setMap(null);
                }
                overlay.setMap(map);
            };
        })(overlay, map));
    }

    // 커스텀 오버레이를 닫기 위해 호출되는 함수
    window.closeOverlay = function (index) {
        overlays[index].setMap(null);
    }
});
