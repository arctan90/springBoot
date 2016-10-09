# springBoot
springBoot for page views
9.20 设计注册与登录前端，注册包含用户名、密码、邮箱、提示问题

10.08 注册实现，登录前端实现，后端需要做session策略
      session策略：登录成功后，向redis的session中写用户名，再向mysql中写sessionId，最后向前端返回这个sessionId
      前端自己向cookie中存这个sessionId，并附带比如用户名的信息，
      之后的前端的业务访问api携带sessionId发给后端
      后端做session一致性校验：
      * 查当前的sessionId和传来的session是否一致，
      * 查redis取出对应的sessionId保持的用户名，到用户表里查对应的sessionId，如果sessionId不一致，或者redis查不到sessionId，就返回要求登录
      
10.09 登录的时候，如果登录成功，服务端将sessionId+name组成了token存在用户表里面，另外将token存在session里面
