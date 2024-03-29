package com.karl.model;

import com.karl.service.BusinessSocketService;

public class Weather {
		private String high;  //最高温度
		private String low;
		private String type;
		private String notice;
		private String city;
		private String date;
		
		
		public String getHigh() {
			return high;
		}

		public void setHigh(String high) {
			this.high = high;
		}

		public String getLow() {
			return low;
		}

		public void setLow(String low) {
			this.low = low;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getNotice() {
			return notice;
		}

		public void setNotice(String notice) {
			this.notice = notice;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		@Override
		public String toString() {
			String content = "";
			content += "城市:" + city + BusinessSocketService.enter;
			content += "日期:" + date + BusinessSocketService.enter;
			content += "最高温度:" + high + BusinessSocketService.enter;
			content += "最低温度:" + low + BusinessSocketService.enter;
			content += "天气:" + type + BusinessSocketService.enter;
			content += "注意事项:" + notice + BusinessSocketService.enter;
		return content;
		}
}
