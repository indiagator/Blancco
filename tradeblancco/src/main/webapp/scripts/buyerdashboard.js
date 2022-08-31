function init()
{
    //alert("productList javascript runs");

    var testHttpRequest;

    function ajaxTestHandler()
    {
        if (testHttpRequest.readyState === XMLHttpRequest.DONE)
        {
            if (testHttpRequest.status === 200) {
                //alert(testHttpRequest.responseText);

                let product_list = JSON.parse(testHttpRequest.responseText)

                for(let product of product_list)
                {
                    let product_info = product.chapter +" <b>"+product.hscode+"</b> "+product.description+" "+product.unit;
                    let para = document.createElement("p")
                    para.innerHTML = product_info;
                    document.getElementById("product_list").appendChild(para);
                }

                //let user_details_obj = JSON.parse(testHttpRequest.responseText);


            } else {
                alert('There was a problem with the request.');
            }
        }
    }

    function makeTestRequest()
    {

        testHttpRequest = new XMLHttpRequest();
        if (!testHttpRequest) {
            alert('Giving up :( Cannot create an XMLHTTP instance');
            return false;
        }

        // alert("Ajax request Created");

        //let username = document.getElementById("username").innerText;
        //dataString = "username="+username;


        testHttpRequest.onreadystatechange = ajaxTestHandler;
        testHttpRequest.open('POST','http://localhost:8082/api/0.1/test-service-3/saveProduct',true);
        //testHttpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        testHttpRequest.send();


    }
    // document.getElementById("show_profile_button").addEventListener("click",makeTestRequest);

    makeTestRequest();

}