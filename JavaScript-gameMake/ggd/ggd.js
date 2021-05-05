var create_pro = document.querySelector('.create_pro'),
            output = document.querySelector('.text'),
            sol_pro = document.querySelector('.sol_pro'),
            submit_pro = document.querySelector('.submit_pro'),
            target = document.querySelector('.target');

        var result = -1;
        create_pro.addEventListener('click', () => {
            var randomInt1 = Math.floor(Math.random() * 9 + 1);
            var randomInt2 = Math.floor(Math.random() * 9 + 1);
            result = randomInt1 * randomInt2;
            output.innerHTML = `${randomInt1} * ${randomInt2} 는?`;
        });

        submit_pro.addEventListener('click', () => {
            console.log(result +" "+sol_pro.value);
            if(result === Number(sol_pro.value)) target.innerHTML = '정답입니다!';
            else target.innerHTML = '틀렸습니다!';
        });