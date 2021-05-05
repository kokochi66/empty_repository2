var res = [];
        var start = document.querySelector('.start'),
            post = document.querySelector('.post'),
            insert = document.querySelector('.insert'),
            submit = document.querySelector('.ins_btn'),
            counting = document.querySelector('.counting');
        var continuing = false, sub_count = 0;

        start.addEventListener('click', () => {
            var pannel = [1,2,3,4,5,6,7,8,9];
            res = [];
            for(var i=0;i<4;i++) {
                res.push(pannel.splice((Math.random()*(9-i)),1)[0]);
            }
            sub_count = 0;
            continuing = true;
            console.log(res);
            post.innerHTML = '게임이 시작되었습니다!';
            counting.innerHTML = `${sub_count}회`;
        });
        submit.addEventListener('click', () => {
            var val = String(insert.value);
            if(val.length > 4 || val.length < 4) {
                post.innerHTML = '4글자를 입력해주세요';
                return;
            }
            if(continuing && sub_count < 15) {
                sub_count++;
                counting.innerHTML = `${sub_count}회`;
                if(val === String(res.join(''))) {
                    continuing = false;
                    post.innerHTML = '정답입니다!';
                } else {
                    var strike = 0, ball = 0;
                    for(var i = 0;i<4;i++) {
                        var c = Number(val.charAt(i));
                        var cent = Number(res.indexOf(c));
                        if(i == cent) strike++;
                        else if(cent >= 0) ball++;
                    }
                    post.innerHTML = `${strike} 스트라이크, ${ball} 볼입니다.`;
                }
            } else if(sub_count >= 15) {
                continuing = false;
                post.innerHTML = '도전회수를 초과했습니다. 패배하였습니다.'
            }
            else {
                post.innerHTML = '게임이 아직 시작되지 않았습니다.';
            }

        });