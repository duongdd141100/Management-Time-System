/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function calTimeSum(time) {
    let totalInSecs = 0
//    console.log(totalInSecs)
    document.querySelectorAll(time).forEach(function (timeElement) {
        const [hou, min, sec] = timeElement.innerHTML.split(':')
        totalInSecs += parseInt(sec) + min * 60 + hou * 3600;
    })
    
    const hou = parseInt(totalInSecs / 3600)
    const min = parseInt(totalInSecs % 3600 / 60)
    const sec = totalInSecs % 60
    return [hou, min, sec].join(":");
}

