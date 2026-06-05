function Stack() {
    this.clear();
}

Stack.prototype.clear = function (value) {
    this.store = [];
    this.index = 0;
};

Stack.prototype.push = function (value) {
    this.store[this.index++] = value;
};

Stack.prototype.pop = function () {
    if (0 < this.index) {
        let value = this.store[--this.index];
        this.store[this.index] = undefined;
        return value;
    } else {
        throw Error('빔');
    }
};

function Point(x, y) {
    this.x = x;
    this.y = y;
}

function Path(sp, ep) {
    this.sp = sp;
    this.ep = ep;
}

function Photoshop(canvas) {
    this.ctx = canvas.getContext("2d");
    let currentPath;

    this.s = new Stack();
    this.rs = new Stack();

    canvas.addEventListener('mousedown', (e) => {
        currentPath = new Path(new Point(e.offsetX, e.offsetY));
    });

    canvas.addEventListener('mouseup', (e) => {
        currentPath.ep = new Point(e.offsetX, e.offsetY);
        this.drawPath(currentPath);
        this.s.push(currentPath);
        this.rs.clear();
    });
}
Photoshop.prototype.drawPath = function (path) {
    this.ctx.beginPath();
    this.ctx.moveTo(path.sp.x, path.sp.y);
    this.ctx.lineTo(path.ep.x, path.ep.y);
    this.ctx.stroke();
}

Photoshop.prototype.drawStack = function () {
    this.ctx.reset();

    this.s.store.forEach((path) => {
        path && this.drawPath(path);
    });
}

Photoshop.prototype.undo = function () {
    this.rs.push(this.s.pop());
    this.drawStack();
}

Photoshop.prototype.redo = function () {
    this.s.push(this.rs.pop());
    this.drawStack();
}