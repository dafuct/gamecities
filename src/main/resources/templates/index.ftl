<!DOCTYPE html>
<html>
<head>
  <title>Cities</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<div>
  <form action="/begin" method="get">
    <h2>Поиграем в игру города? Название города должно быть с большой буквы.</h2>
    <h3>Комьютер назвал город:
        <#if city??>
            ${city}
        </#if>
    </h3>
  </form>

  <form action="/next" method="get">
    <h3>
        <#if errorMessageWrongCity??>
            ${errorMessageWrongCity}
        </#if>
        <#if errorMessageNoCity??>
            ${errorMessageNoCity}
        </#if>
        <#if errorMessageLost??>
            ${errorMessageLost}
        </#if>
        <#if emptyInput??>
            ${emptyInput}
        </#if>
    </h3>
    <h3>
      User введи город на букву:
        <#if lastSymbol??>
            ${lastSymbol}
        </#if>:
      <input type="text" name="word"/>
      <input type="submit" value="сказать">
    </h3>
  </form>

  <form action="/end" method="post">
    <input type="submit" value="Закончить игру">
  </form>
</div>
</body>
</html>
