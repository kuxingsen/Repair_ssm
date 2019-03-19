'use strict';

$('#takeRecordModal').on('show.bs.modal', function (event) {
  var button = $(event.relatedTarget);
  var id = button.data('id');
  $(this).find('.hideId').val(id);
});
$('#priceModal').on('show.bs.modal', function (event) {
  var button = $(event.relatedTarget);
  var id = button.data('id');
  $(this).find('.hideId').val(id);
});
$('#priceSubmit').on('click', function () {
  var price = $('#price').val();
  var id = $('#priceModal').find('.hideId').val();
  if (price.length === 0) {
    layer.alert('价格不能为空');
  } else if (isNaN(price) || price < 0) {
    layer.alert('请输入正确的价格');
  } else {
    var load = layer.load(1);
    $.post('/setPartPrice', {
      recordId: id,
      partPrice: price
    }, function (res) {
      layer.close(load);
      if (res.success) {
        layer.alert('设置零件价格成功', function () {
          location.reload();
        });
      } else {
        layer.alert(res.msg);
      }
    });
  }
});
$('#submitBtn').on('click', function () {
  var sellerName = $('#sellerName').val();
  var sellerPhone = $('#sellerPhone').val();
  var id = $('#takeRecordModal').find('.hideId').val();
  if (sellerPhone.length === 0 || sellerName.length === 0) {
    layer.alert('表单内容不能留空');
    return;
  }
  var load = layer.load(1);
  // DO: AJAX提交
  $.post('/takeRecord', {
    sellerName: sellerName,
    sellerPhone: sellerPhone,
    recordId: id
  }, function (res) {
    layer.close(load);
    if (res.success) {
      layer.alert('接单成功', function () {
        location.reload();
      });
    } else {
      layer.alert(res.msg, function () {
        location.reload();
      });
    }
  });
});

(function ($) {
  var load = layer.load(1);
  $.get('/getAllRecord', function (res) {
    layer.close(load);
    var dt = res.data;
    if (res.success) {
      for (var i = 0; i < dt.length; i++) {
        var dom = '<tr>\n                          <th scope="row">' + (i + 1) + '</th>\n                          <td>' + (dt[i].status == 0 ? '未维修' : dt[i].status == 1 ? '已接单' : dt[i].status == 2 ? '维修中' : dt[i].status == 3 ? '待支付' : '已支付') + '</td>\n                          <td>' + dt[i].name + '</td>\n                          <td>' + dt[i].address + '</td>\n                          <td>' + dt[i].content + '</td>\n                          <td>\n                            <button\n                              class="btn ' + (dt[i].status == 1 ? 'btn-warning' : dt[i].status == 0 ? 'btn-primary' : '') + ' btn-sm"\n                              data-toggle="modal"\n                              data-target=' + (dt[i].status == 0 ? '#takeRecordModal' : '#priceModal') + '\n                              data-id="' + dt[i].id + '"\n                              ' + (dt[i].status != 0 && dt[i].status != 1 ? 'disabled' : '') + '\n                            >\n                              ' + (dt[i].status == 0 ? '接单' : dt[i].status == 1 ? '价格' : dt[i].status == 4?'完成':'等待') + '\n                            </button>\n                          </td>\n                        </tr>';
        $('#table').append(dom);
      }
    }
  });
})(jQuery);


//
// $('#takeRecordModal').on('show.bs.modal', function (event) {
//   var button = $(event.relatedTarget);
//   var id = button.data('id');
//   $(this).find('.hideId').val(id);
// });
// $('#priceModal').on('show.bs.modal', function (event) {
//   var button = $(event.relatedTarget);
//   var id = button.data('id');
//   $(this).find('.hideId').val(id);
// });
// $('#priceSubmit').on('click', function () {
//   var price = $('#price').val();
//   var id = $('#priceModal').find('.hideId').val();
//   if(price.length === 0) {
//     layer.alert('价格不能为空');
//   } else if(isNaN(price) || price < 0) {
//     layer.alert('请输入正确的价格');
//   } else {
//     var load = layer.load(1);
//     $.post('/setPartPrice', {
//       recordId: id,
//       partPrice: price
//     }, function (res) {
//       layer.close(load);
//       if(res.success) {
//         layer.alert('设置零件价格成功', function () {
//           location.reload();
//         });
//       } else {
//         layer.alert(res.msg);
//       }
//     })
//   }
// });
// $('#submitBtn').on('click', function () {
//   var sellerName = $('#sellerName').val();
//   var sellerPhone = $('#sellerPhone').val();
//   var id = $('#takeRecordModal').find('.hideId').val();
//   if (sellerPhone.length === 0 || sellerName.length === 0) {
//     layer.alert('表单内容不能留空');
//     return;
//   }
//   var load = layer.load(1);
//   // DO: AJAX提交
//   $.post('/takeRecord', {
//     sellerName: sellerName,
//     sellerPhone: sellerPhone,
//     recordId: id
//   }, function (res) {
//     layer.close(load);
//     if (res.success) {
//       layer.alert('接单成功', function () {
//         location.reload();
//       });
//     } else {
//       layer.alert(res.msg, function () {
//         location.reload();
//       });
//     }
//   });
// });
//
// (function($) {
//   var load = layer.load(1);
//   $.get('/getAllRecord', function (res) {
//     layer.close(load);
//     const dt = res.data;
//     if(res.success) {
//       for(var i=0; i<dt.length; i++) {
//         var dom = `<tr>
//                           <th scope="row">${i+1}</th>
//                           <td>${dt[i].status===0?'未维修': dt[i].status===1?'已接单':dt[i].status===2?'维修中':dt[i].status===3?'待支付':'已支付'}</td>
//                           <td>${dt[i].name}</td>
//                           <td>${dt[i].address}</td>
//                           <td>${dt[i].content}</td>
//                           <td>
//                             <button
//                               class="btn ${dt[i].status===1?'btn-warning':dt[i].status===0?'btn-primary':''} btn-sm"
//                               data-toggle="modal"
//                               data-target=${dt[i].status===0?'#takeRecordModal':'#priceModal'}
//                               data-id="${dt[i].id}"
//                               ${dt[i].status!==0&&dt[i].status!==1?'disabled':''}
//                             >
//                               ${dt[i].status===0?'接单':dt[i].status===1?'价格':'等待'}
//                             </button>
//                           </td>
//                         </tr>`;
//         $('#table').append(dom);
//       }
//     }
//   })
// })(jQuery);