
$(document).ready(()=>{
    a = new Map();
    a.set('a', 1);
    a.set('b', 2);
    a.set('c', 3);
    a.delete('b');
    console.log(a);

    var tests = {};
    var test = {};
    var arr = [];
    test.id = 1
    test.name = 'apple';
    arr.push({
        ...test
    });
    test.id = 2
    test.name = 'banana';
    arr.push({
        ...test
    });
    tests['test'] = arr;
    console.log(JSON.stringify(test));
    console.log(JSON.stringify(tests));
})