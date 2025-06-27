const canvas = document.getElementById('bgCanvas');
const ctx = canvas.getContext('2d');

function resizeCanvas() {
    canvas.width = window.innerWidth;
    canvas.height = window.innerHeight;
}
resizeCanvas();
window.addEventListener('resize', resizeCanvas);

// مكان الماوس
let mouse = { x: -1000, y: -1000 }; // نبدأ برا الشاشة عشان يشتغل حتى لو ثابت

// إنشاء نقاط الشبكة العصبية
let points = [];
for (let i = 0; i < 600; i++) {
    points.push({
        x: Math.random() * canvas.width,
        y: Math.random() * canvas.height,
        vx: (Math.random() - 0.5) * 1,
        vy: (Math.random() - 0.5) * 1
    });
}

function draw() {
    ctx.clearRect(0, 0, canvas.width, canvas.height);

    for (let i = 0; i < points.length; i++) {
        let p = points[i];

        // تحريك النقطة عشوائيًا (حركة عائمة)
        p.vx += (Math.random() - 0.5) * 0.05;
        p.vy += (Math.random() - 0.5) * 0.05;

        // تأثير الماوس (طرد من الماوس)
        let dx = p.x - mouse.x;
        let dy = p.y - mouse.y;
        let dist = Math.sqrt(dx * dx + dy * dy);

        if (dist < 60) {
            let angle = Math.atan2(dy, dx);
            let force = (60 - dist) / 60;
            let fx = Math.cos(angle) * force * 2;
            let fy = Math.sin(angle) * force * 2;

            p.vx += fx;
            p.vy += fy;
        }

        // تحديث موقع النقطة
        p.x += p.vx;
        p.y += p.vy;

        // تقليل السرعة (احتكاك خفيف)
        p.vx *= 0.95;
        p.vy *= 0.95;

        // ارتداد من الحواف
        if (p.x < 0 || p.x > canvas.width) p.vx *= -1;
        if (p.y < 0 || p.y > canvas.height) p.vy *= -1;

        // رسم النقطة
        ctx.beginPath();
        ctx.arc(p.x, p.y, 2, 0, Math.PI * 2);
        ctx.fillStyle = '#00f0ff';
        ctx.fill();

        // رسم خطوط الاتصال بين النقاط القريبة
        for (let j = i + 1; j < points.length; j++) {
            let p2 = points[j];
            let d = Math.sqrt((p.x - p2.x) ** 2 + (p.y - p2.y) ** 2);

            if (d < 70) {
                ctx.beginPath();
                ctx.moveTo(p.x, p.y);
                ctx.lineTo(p2.x, p2.y);
                ctx.strokeStyle = `rgba(0,240,255,${1 - d / 70})`;
                ctx.stroke();
            }
        }
    }

    requestAnimationFrame(draw);
}

// التقاط موقع الماوس
document.addEventListener('mousemove', (e) => {
    mouse.x = e.clientX;
    mouse.y = e.clientY;
});

draw();
