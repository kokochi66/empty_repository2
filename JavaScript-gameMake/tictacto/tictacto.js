var main = document.getElementById('main_wrap'),
    pans = main.querySelectorAll('.pan'),
    post = document.querySelector('.post'),
    pans_arr = [];
for(var i=0;i<3;i++) {
    pans_arr[i] = [];
    for(var j=0;j<3;j++) {
        pans_arr[i].push(pans[(i*3) + j])
    }
}

var turn = 'X';
post.innerHTML = `${turn}의 차례입니다.`;
pans_arr.forEach((item, index, arr) => {
    item.forEach((it,idx, ar_) => {
        it.addEventListener('click', () => {
            if(it.classList.contains('active')) {
                post.innerHTML = `이미 채워져있습니다.`;
            } else {
                it.classList.add('active');
                if(turn === 'X') {
                    it.classList.add('Xactive');
                    turn = 'O';
                    post.innerHTML = `${turn}의 차례입니다.`;
                } else {
                    it.classList.add('Oactive');
                    turn = 'X';
                    post.innerHTML = `${turn}의 차례입니다.`;
                }
            }
            
        });
    })
});
