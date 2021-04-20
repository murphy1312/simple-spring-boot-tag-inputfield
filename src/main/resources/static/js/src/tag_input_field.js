var es = document.querySelectorAll('.custom_tag_input');
for (var i = 0; i < es.length; i++) {
	es[i]._list = es[i].querySelector('ul');
	es[i]._input = es[i].querySelector('.custom_tag_input_input');
	es[i]._input._icategories = es[i];
	es[i].onkeydown = function (e) {
		var e = event || e;
		var inputField = e.target;
		var c = e.target._icategories;
		var ul = c.querySelector('ul');
		var currentLength = ul.children.length;
		var listName = ul.id;
		// keycode enter -> add new element
		if (e.keyCode == 13) {
			if(inputField.value.length > 0)
			{
				var li = document.createElement('li');
				li.innerHTML = c._input.value;
				var inputHidden = document.createElement('input');
				inputHidden.type = "hidden";
				inputHidden.name = listName + "[" + currentLength + "]";
				inputHidden.value = c._input.value;
				li.appendChild(inputHidden);
				c._list.appendChild(li);
				c._input.value = '';
			}
			e.preventDefault();  // prevent sending the form via enter
		}
		// backspace keycode -> delete last
		if(e.keyCode == 8) {
			if(inputField.value.length <= 0)
			{
				var lastChild = c._list.children[currentLength -1];
				var value = lastChild.querySelector('input').value;
				inputField.value = value;
				c._list.removeChild(lastChild);
				e.preventDefault(); // prevent the last character of the input field before being removed
			}
		}
	}
}
