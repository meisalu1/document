$(document).ready( function() {
	$('form.ajaxform').on('submit', function(event) {
		event.preventDefault();
		$form = $(this);
		$.ajax({
			data: $form.serialize(),
			method: "POST"
		}).done(function(data) {
			
			var jsonData = $.parseJSON(data);
			
			console.log(jsonData);
			
			var $hiddenForm = $('form.hidden').clone().removeClass('hidden');
			var doc = jsonData[0];
			var $row = $hiddenForm.find('div.hidden');
			var $tableRow = $form.parent().parent();
			var $currentRow = null;
			var attributeBlock = '';
			
			$hiddenForm.find('div.row > input[name="name"]').val(doc.name);
			$hiddenForm.find('div.row > input[name="desc"]').val(doc.desc);
			$hiddenForm.find('div.row > input[name="id"]').val(doc.id);
			$hiddenForm.find('div.row > input[name="type"]').val(doc.type);
			$hiddenForm.find('div.row > select[name="status"]').val(doc.status);
			
			$hiddenForm.on('submit', function(event){
				event.preventDefault();
				var $editForm = $(this);
				$.ajax({
					data: $editForm.serialize(),
					method: "POST"
				}).done(function(updateData) {
					var messages = $.parseJSON(updateData);
					removeErrors($editForm);
					console.log(messages);
					if(messages[0] == true){
						SetHeaderMessage(messages[1], $editForm, "success");
					} else {
						populateErrors(messages[1], $editForm);
						SetHeaderMessage("Formi salvestamine ebaonnestus vigaste andmete tottu", $editForm, "error");
					}
				});
			});
			
			jsonData[1].forEach( function(attribute){
				$currentRow = $row.clone();
				$currentRow.find('label').text(attribute.name);
				$currentRow.append(createRow(attribute, doc["attrs"][attribute.attribute_type]));
				$currentRow.removeClass('hidden');
				attributeBlock += $currentRow.get(0).outerHTML;
				console.log($currentRow);
			});
			$row.after(attributeBlock);
			$tableRow.after('<tr class="hidden changeForm"><td colspan="5"></td></tr>');
			$tableRow.next().find('td').append($hiddenForm);
			$tableRow.next().show("slow");
			console.log($hiddenForm);
		})
		.fail(function(data) {
			console.log("Problem with processing your request !");
		});
	});
	
	$('form.ajaxdelete').on('submit', function(event) {
		event.preventDefault();
		var $form = $(this);
		var $submit = $form.find('input[type="submit"]');
		$submit.attr("disabled", "disabled");
		$.ajax({
			data: $form.serialize(),
			method: "POST"
		}).done(function(data) {
			$form.parent().parent().remove();
		});
	});
	
	$('form.ajaxcache').live('submit', function(event) {
		event.preventDefault();
		var $form = $(this);
		$.ajax({
			data: $form.serialize(),
			method: "POST"
		}).done(function(data) {
			var doc = $.parseJSON(data);
			var $table = $('table.cachetable > tbody');
			var $row = $table.find('tr.example');
			var $newrow = $row.clone();
			$newrow.find('td:eq(0)').text(doc.id);
			$newrow.find('td:eq(1)').text(doc.name);
			$newrow.find('td:eq(2)').text(doc.desc);
			$newrow.find('input[name="id"]').val(doc.id);
			$newrow.find('input[type="checkbox"]').attr('name', 'doc_{0}'.format(doc.id)).val(doc.id);
			if($table.find('tr:visible').length == 1)
				$table.find('tr#empty').addClass('hidden');
			$newrow.removeClass('hidden').removeClass('example');
			$row.before($newrow);
		});
	});
	
	$('form.ajaxcacheremove').live('submit', function(event) {
		event.preventDefault();
		var $form = $(this);
		$.ajax({
			data: $form.serialize(),
			method: "POST"
		}).done(function(data) {
			$form.parent().parent().remove();
			$table = $('table.cachetable > tbody');
			if($table.find('tr:visible').length == 0) {
				$table.find('tr#empty').removeClass('hidden');
			}
		});
	});
	
	$('form.ajaxcacheremove').live('submit', function(event) {
		event.preventDefault();
		var $form = $(this);
		$.ajax({
			data: $form.serialize(),
			method: "POST"
		}).done(function(data) {
			$form.parent().parent().remove();
			var $table = $('table.cachetable > tbody');
			if($table.find('tr:visible').length == 0) {
				$table.find('tr#empty').removeClass('hidden');
			}
		});
	});
	
	$('form.ajaxselectedcache').on('submit', function(event) {
		event.preventDefault();
		var $form = $(this);
		var selected = new Array();
		$('table.cachetable tbody > tr:visible > td > input[type="checkbox"]:checked').each( function(index, elem){
			selected.push($(elem).val());
			$(elem).parent().parent().remove();
		});
		console.log(selected);
		$.ajax({
			data: $form.serialize() + "&selected=" +selected,
			method: "POST"
		}).done(function(data) {
			console.log(data);
		});
	});
	
	$('form.ajaxemptycache').on('submit', function(event) {
		event.preventDefault();
		var $form = $(this);
		$.ajax({
			data: $form.serialize(),
			method: "POST"
		}).done(function(data) {
			$table = $('table.cachetable tbody');
			$table.find('tr:visible').remove();
			$table.find('#empty').removeClass('hidden');
		});
	});
	
});

function createRow(attribute, value) {

	var label = '<label for="attr_{0}">{1}</label> '.format(attribute.attribute_type, attribute.name);
	var input = '';
	
	if (attribute.data_type == 4) {
		input = '<select name="attr_{0}">'.format(attribute.attribute_type);
		attribute.selections.forEach(function(select) {
			input += '<option value={0} {1}>{2}</option>'.format(select.id, (select.id == parseInt(value) ? 'selected="selected"' : ''), select.name);
		});
		input += '</select>';
	} else {
		input += '<input type="text" name="attr_{0}" value="{1}"/>'.format(attribute.attribute_type, value);
	}
	return label + input;
}

String.prototype.format = function() {
   var content = this;
   for (var i=0; i < arguments.length; i++) {
        var replacement = '{' + i + '}';
        content = content.replace(replacement, arguments[i]);  
   }
   return content;
}

function populateErrors(errors, $form) {
	console.log(errors);
	console.log($form);
	$form.find('div.row > input, div.row > select').each( function() {
		var $this = $(this);
		var name = $this.attr('name');
	
		if(errors[name] !== undefined)
			$this.after('<span class="error">{0}</span>'.format(errors[name]));
	});
}

function removeErrors($form) {
	$form.find('span.error, span.success').remove();
}

function SetHeaderMessage (msg, $form, spanClass) {
	$form.prepend('<span class="{0}">{1}</span>'.format(spanClass, msg));
}
