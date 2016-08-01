
INSERT INTO `Role`(`Id`, `roleName`) VALUES ('1','ADMIN');
INSERT INTO `Role`(`Id`, `roleName`) VALUES ('2','GUEST');
INSERT INTO `Role`(`Id`, `roleName`) VALUES ('3','USER');
INSERT INTO `Package` (`Id`, `packageName`, `amount`) VALUES ('1', '1PKG', '500');
INSERT INTO `Package` (`Id`, `packageName`, `amount`) VALUES ('2', '2PKG', '800');
INSERT INTO `Package` (`Id`, `packageName`, `amount`) VALUES ('3', '3PKG', '1000');
INSERT INTO `Customer` (`Id`, `address`, `customerId`, `emailAddress`, `mobileNumber`, `name`, `password`, `status`, `package_id`)
VALUES
  ('1', '126/1 North Mugdapara', 'arifen@yahoo', 'sarifenju@gmail.com', '01918642075', 'arifen', '123456', 'active',
   '1');