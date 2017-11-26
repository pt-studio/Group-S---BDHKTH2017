# Group-S---BDHKTH2017
<h1>Dự án chậu cây thông minh</h1>
<h3>Mô tả vấn đề:</h3>
<p>Hiện nay, trong cuộc sống đầy khói bụi của thành phố, nhiều người có nhu cầu tạo ra những không gian xanh trong gia đình, công sở bằng cách trồng cây cảnh. Tuy nhiên không phải ai cũng có thể tạo được không gian xanh cho mình vì thiếu thời gian chăm sóc và quản lý, thiếu kiến thức về chăm sóc cây cảnh hay thậm chí là vì thiếu động lực trong việc tạo nên một không gian xanh cho chính mình.<p>

<h3>Ý tưởng và giải pháp:</h3>
<p>Để giải quyết những vấn đề trên, nhóm đề xuất ý tưởng xây dựng một hệ thống giúp quản lý và tương tác với cây cảnh tích hợp các công nghệ IoT, khai thác dữ liệu, thực tại tăng cường và xử lý hình ảnh.
Một chậu cây cảnh sẽ được gắn các cảm biến về ánh sáng, độ ẩm, nhiệt độ, ... Những cảm biến này sẽ liên tục thu nhận tín hiệu từ môi trường xung quanh cây cảnh và gửi trả dữ liệu về server. Những dữ liệu này sẽ được phân tích để đánh giá tình trạng hiện tại của cây cảnh. Ví dụ khi độ ẩm trong đất dưới một ngưỡng nào đó thì có thể dẫn đến tình trạng cây thiếu nước hay độ sáng không đủ có thể dẫn đến cây bị thiếu sáng.
Thông tin về tình trạng của cây sẽ được hiển thị lên cho người dùng thông qua tính năng thực tại tăng cường. Khi người dùng quay camera về phía cây cảnh, hình ảnh của cây cùng với khuôn mặt cảm xúc sẽ được hiển thị trên màn hình điện thoại. Cảm xúc được hiển thị sẽ tùy vào tình trạng của cây. Khi cây thiếu sáng hay thiếu nước, biểu tượng cảm xúc trên cây sẽ là buồn. Khi cây trong điều kiện tốt thì biểu tượng cảm xúc trên cây sẽ là vui. Việc tương tác với cây cảnh không chỉ dừng lại ở việc hiển thị tình trạng của cây mà người dùng còn có thể tương tác như đụng vào cây sẽ biểu hiện những cảm xúc như mắc cỡ hay giận giữ tạo thêm sự sống động cho cây cảnh. Người dùng thậm chí có thể "nói chuyện" với cây cảnh nhờ vào một chatbot được tích hợp trong ứng dụng. Ví dụ khi phát hiện cây đang trong tình trạng không tốt, người dùng có thể hỏi trực tiếp cây muốn tưới nước hay muốn bón phân và cây sẽ trả lời dựa trên các thông tin tình trạng của cây.
Nếu người dùng không ở gần cây cảnh, thông tin tình trạng sẽ vẫn có thể được hiển thị thông qua giao diện ứng dụng. Như vậy, người dùng vẫn có thể xem tình trạng cây cảnh của mình khi đang ở công sở. Chậu cây cũng được tính hợp cơ chế thẩm thấu, cho phép người dùng có thể không cần tưới trong thời gian đi công tác hay đi chơi xa.
Trong trường hợp người dùng không biết nên làm gì với các thông tin tình trạng của cây hay cách chăm sóc một loại cây nào đó sao cho hiệu quả, người dùng có thể sử dụng tính năng hỗ trợ về kiến thức cây cảnh của ứng dụng. Cơ sở dữ liệu của ứng dụng sẽ lưu trữ một số phương pháp trồng một số loại cây cảnh được khai thác từ những người có kinh nghiệm về cây cảnh. Người dùng cũng có thể góp phần làm giàu thêm cơ sở dữ liệu bằng cách bổ sung thêm tri thức của mình vào ứng dụng.
Ứng dụng không chỉ bổ ích cho cây cảnh mà còn có ích cho người dùng thông qua việc đưa những kiến thức bổ ích đến với người dùng thông qua các notifications.<p>
 
<h3>Giá trị của ứng dụng:</h3>
<p>Hệ thống có giá trị sử dụng cao.</p>
<p>Về giá trị trước mắt, ứng dụng giúp giải quyết được các vấn đề mà nhóm đề ra. Với những tính năng về quản lý trình trạng cây cảnh và cung cấp thông tin cần thiết, người dùng có thể dễ dàng chăm sóc cây cảnh cho dùng không phải là người có kiến thức chuyên môn. Đặc biệt, tính năng tương tác với cây cảnh thông qua thực tại tăng cường và trí tuệ nhân tạo tạo nên sự sinh động và thú vị trong việc chăm sóc cây cảnh. Qua lăng kính của ứng dụng, cây cảnh trở nên sống động như một vật nuôi trong nhà và có thể làm bạn với người dùng.<p>
<p>Về giá trị lâu dài, việc khuyến khích người dùng trông cây cảnh sẽ phần nào giúp tạo những không gian xanh tốt cho tinh thần và sức khỏe người dùng. Hơn nữa, những kiến thức được lồng ghép trong ứng dụng là một nguồn tri thức bổ ích có thể đem lại nhiều lợi ích cho người dùng.<p>

<h3>Kiến trúc hệ thống</h3>
<p>- Back-end web viết bằng Django, đưa ra API cho front-end</p>
<p>- Front-end gồm web và mobile gọi api của Back-end</p>
<p>- Sử dụng Unity phục vụ cho chức năng thực tại tăng cường</p>
<p>- Hệ thống cảm biến nhiệt độ, độ ẩm, ánh sáng tích hợp thành 1 thiết bị để gắn vào chậu cây</p>
<p>- Chậu cây có hệ thống thẩm thấu nước dưới đáy</p>
