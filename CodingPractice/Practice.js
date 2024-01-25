var an_obj = {
    bar: function () {
        var x = (() => this);
        return x;
    }

};

var fn = an_obj.bar();
fn();
var fn2 = an_obj.bar;
fn2()();